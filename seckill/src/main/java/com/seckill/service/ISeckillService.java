package com.seckill.service;


import javax.servlet.http.HttpServletRequest;

import com.seckill.base.result.Result;
import com.seckill.model.Course;
import com.seckill.model.Orders;
import com.seckill.model.User;

public interface ISeckillService {

    Result<Orders> seckillFlow(User user, String courseNo);

    void cacheAllCourse();

    Orders seckill(User user, Course course);

    Result<Orders> seckillResult(User user, String courseNo);

    String getPath(User user, String courseNo);

    Result<Orders> seckillFlow(User user, String courseNo, String path);

    Result<Orders> seckillFlow(User user, String courseNo, String path, HttpServletRequest request);

}
