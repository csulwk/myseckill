package com.seckill.kafka;

/**
 * kafka消费者来监听topic
 */

import com.seckill.model.Course;
import com.seckill.model.Orders;
import com.seckill.service.ICourseService;
import com.seckill.service.IOrderService;
import com.seckill.service.ISeckillService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.seckill.model.User;

@Component
@Slf4j
public class KafkaConsumer {

    @Autowired
    public ICourseService courseService;

    @Autowired
    public IOrderService orderService;

    @Autowired
    public ISeckillService seckillService;

    @KafkaListener(id="seconds-kill", topics = "test", groupId = "seconds-kill")
    public void listener(ConsumerRecord<?, ?> record) {

        //处理接收到的data record.value()
        String[] messages = record.value().toString().split(",");



        String courseNo  = messages[0];
        String username  = messages[1];

        log.info("===============================================");
        log.info(" 三 /Kafka consumer 里面的username" + username );
        log.info("===============================================");

        //在业务层已经过滤了很多请求，这里开始直接访问数据库
        Course course = courseService.findCourseByCourseNo(courseNo);
        int stock = course.getStockQuantity();
        if(stock <= 0){
            return ;
        }
        //判断是否已经购买
        Orders order = orderService.getOrderByUsernameAndCourseNo(username, courseNo);
        if(order != null){
            //如果订单不为空，就表示已经购买，则直接返回
            return ;
        }
        //减库存 下订单
        User user = new User();
        user.setUsername(username);
        seckillService.seckill(user, course);
    }
}
