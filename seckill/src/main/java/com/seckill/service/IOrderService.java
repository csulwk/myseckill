package com.seckill.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.seckill.model.entity.Orders;

public interface IOrderService {

    Orders getOrderByUsernameAndCourseNo(String username, String courseNo);

    Orders saveAndFlush(Orders orders);

    List<Orders> findAllByUsername(String username, Sort sort);

}
