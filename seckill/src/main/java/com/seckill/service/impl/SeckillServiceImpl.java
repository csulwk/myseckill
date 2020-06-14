package com.seckill.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.seckill.base.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.seckill.base.result.Result;
import com.seckill.base.result.ResultCode;
import com.seckill.model.Course;
import com.seckill.model.Orders;
import com.seckill.model.User;
import com.seckill.redis.CourseRedis;
import com.seckill.redis.SeckillRedis;
import com.seckill.service.ICourseService;
import com.seckill.service.IOrderService;
import com.seckill.service.ISeckillService;
import com.seckill.util.IpUtil;
import com.seckill.util.UUIDUtil;

/**
 *
 * @author kai
 * @date 2020-6-10 23:11
 */
@Service("seckillService")
@Transactional(rollbackOn = Exception.class)
@Slf4j
public class SeckillServiceImpl implements ISeckillService{

    private ICourseService courseService;
    private IOrderService orderService;
    private CourseRedis courseRedis;
    private SeckillRedis seckillRedis;
    /**
     * 使用KafkaTempalte进行发送消息
     */
    private KafkaTemplate<String, String > kafkaTempalte;
    /**
     * 通过redis中的数据变化，来初始化这个变量
     * 这是在本地内存中加一个标志位flag, 判断是否被秒杀，若没有库存为true
     */
    private static Map<String, Boolean> isSeckill = new HashMap<>();

    @Autowired
    public SeckillServiceImpl(ICourseService courseService, IOrderService orderService,
                              CourseRedis courseRedis, SeckillRedis seckillRedis,
                              KafkaTemplate<String, String > kafkaTempalte) {
        this.courseService = courseService;
        this.orderService = orderService;
        this.courseRedis = courseRedis;
        this.seckillRedis = seckillRedis;
        this.kafkaTempalte = kafkaTempalte;
    }

    @Override
    public Orders seckill(User user, Course course){
        //减库存
        int success = courseService.reduceStockByCourseNo(course.getCourseNo());

        log.info("===============================================");
        log.info("四、Orders 方法里的username: " + user.getUsername() );
        log.info("===============================================");

        //下订单
        if(success > 0){
            Orders orders = new Orders();
            BeanUtils.copyProperties(course, orders);
            orders.setUsername(user.getUsername());
            orders.setCreateBy(user.getUsername());
            orders.setCreateDate(new Date());
            orders.setPayPrice(course.getCoursePrice());
            orders.setPayStatus("0");
            return orderService.saveAndFlush(orders);
        }
        return null;
    }

    @Override
    public Result<Orders> seckillFlow(User user, String courseNo) {
        log.info(" user = "+user.getUsername());
        return seckillCourse(user, courseNo);
    }

    @Override
    public void cacheAllCourse() {
        log.info("CacheAllCourse...");
        //从数据库中读出来并缓存数据到Redis中
        List<Course> courseList = courseService.findAllCourses();
        if(courseList == null){
            return;
        }
        for(Course course : courseList){
            log.info("CourseNO:{}, Course:{}", course.getCourseNo(), JSONObject.toJSONString(course));
            //60秒过期
            courseRedis.putString(course.getCourseNo(), course.getStockQuantity(), 60, true);
            //为了方便，再把course 这个对象直接存进来
            courseRedis.put(course.getCourseNo(), course, -1);
            //这里相当于初始化这个变量，所以要在这里给这个flag赋值
            isSeckill.put(course.getCourseNo(), false);
        }

    }

    @Override
    public Result<Orders> seckillResult(User user, String courseNo) {
        Orders orders = orderService.getOrderByUsernameAndCourseNo(user.getUsername(), courseNo);
        if(orders == null){
            return Result.failure(ResultCode.SECKILL_LINE_UP);
        }
        return Result.success(orders);
    }

    @Override
    public String getPath(User user, String courseNo) {
        String path = UUIDUtil.getUUID();
        seckillRedis.putString("path"+"_"+courseNo+"_"+user.getUsername(), path, 60);
        return path;
    }

    @Override
    public Result<Orders> seckillFlow(User user, String courseNo, String path, HttpServletRequest request) {
        //ip验证
        String ip = IpUtil.getIpAddr(request);
        log.info("IP: {}", ip);

        if(seckillRedis.incr(ip, 1) >= Constants.IP_REQUEST_MAX_NUM){
            return Result.failure(ResultCode.SECKILL_IP_OUTMAX);
        }
        //验证path
        String redisPath = (String) seckillRedis.getString("path"+"_"+courseNo+"_"+user.getUsername());
        //如果地址不相同。返回错误信息
        if(!path.equals(redisPath)){
            return Result.failure(ResultCode.SECKILL_PATH_ERROR);
        }
        return seckillCourse(user, courseNo);
    }

    /**
     * 抢购课程
     * @param user user
     * @param courseNo courseNo
     * @return Result<Orders>
     */
    private Result<Orders> seckillCourse(User user, String courseNo) {
        boolean isPass = isSeckill.get(courseNo);
        if(isPass){
            return Result.failure(ResultCode.SECKILL_NO_QUOTE);
        }
        //判断库存redis 预减库存
        double stockQuantity = courseRedis.decr(courseNo, -1);
        if(stockQuantity <= 0){
            isSeckill.put(courseNo, true);
            return Result.failure(ResultCode.SECKILL_NO_QUOTE);
        }
        //判断是否已经购买
        Orders order = orderService.getOrderByUsernameAndCourseNo(user.getUsername(), courseNo);
        if(order != null){
            return Result.failure(ResultCode.SECKILL_BOUGHT);
        }

        log.info("===============================================");
        log.info(" 二、seckillCourse 方法里的username: " + user.getUsername() );
        log.info("===============================================");
        //减库存 下订单
        kafkaTempalte.send("test",courseNo+","+ user.getUsername());
        //Orders newOrder = seckill(user, course);
        return Result.failure(ResultCode.SECKILL_LINE_UP);
    }

}
