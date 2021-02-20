package com.seckill.service;


import javax.servlet.http.HttpServletRequest;

import com.seckill.model.Result;
import com.seckill.model.entity.Course;
import com.seckill.model.entity.Orders;
import com.seckill.model.entity.User;

public interface ISeckillService {

    Result<Orders> seckillFlow(User user, String courseNo);

    /**
     * 用Redis缓存所有的课程
     */
    void cacheAllCourse();

    Orders seckill(User user, Course course);

    Result<Orders> seckillResult(User user, String courseNo);

    String getPath(User user, String courseNo);

    Result<Orders> seckillFlow(User user, String courseNo, String path, HttpServletRequest request);

}
