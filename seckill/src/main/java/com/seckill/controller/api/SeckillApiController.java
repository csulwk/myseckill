package com.seckill.controller.api;


import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.seckill.config.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.seckill.base.controller.BaseApiController;
import com.seckill.base.result.Result;
import com.seckill.model.Orders;
import com.seckill.model.User;
import com.seckill.service.ISeckillService;

@RestController
@Slf4j
public class SeckillApiController extends BaseApiController implements InitializingBean{

    @Autowired
    public ISeckillService seckillService;

    @Override
    public void afterPropertiesSet() throws Exception {
        //缓存所有的课程
        seckillService.cacheAllCourse();
    }

    @RequestMapping(value="{path}/seckill/{courseNo}",method=RequestMethod.GET)
    public Result<Orders> seckill(@CurrentUser User user, @PathVariable String courseNo,
                                  @PathVariable String path, HttpServletRequest request){
        log.info("seckill: user={}, courseNo={}, path={}", JSONObject.toJSONString(user), courseNo, path);
        if(user == null){
            return Result.failure();
        }
        log.info("===============================================");
        log.info(" 1、API里的username: " + user.getUsername() );
        log.info("===============================================");
        return seckillService.seckillFlow(user, courseNo, path, request);
    }

    @RequestMapping(value="seckill/{courseNo}",method=RequestMethod.GET)
    public Result<Orders> seckill(@CurrentUser User user, @PathVariable String courseNo){
        log.info("seckill: user={}, courseNo={}", JSONObject.toJSONString(user), courseNo);
        if(user == null){
            return Result.failure();
        }
        return seckillService.seckillFlow(user, courseNo);
    }

    /**
     * 用于前端轮询， 用于给前端显示 订单是否完成： 1.排队中， 2. 已完成
     * @param user user
     * @param courseNo courseNo
     * @return Result<Orders>
     */
    @RequestMapping(value="seckillResult/{courseNo}",method=RequestMethod.GET)
    public Result<Orders> seckillResult(@CurrentUser User user, @PathVariable String courseNo){
        log.info("seckillResult: user={}, courseNo={}", JSONObject.toJSONString(user), courseNo);
        if(user == null){
            return Result.failure();
        }
        return seckillService.seckillResult(user, courseNo);
    }

    @RequestMapping(value="getPath/{courseNo}",method=RequestMethod.GET)
    public String getPath(@CurrentUser User user, @PathVariable String courseNo){
        log.info("getPath: user={}, courseNo={}", JSONObject.toJSONString(user), courseNo);
        if(user == null){
            return "user is null!";
        }
        return seckillService.getPath(user, courseNo);
    }
}
