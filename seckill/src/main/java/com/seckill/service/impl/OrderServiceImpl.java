package com.seckill.service.impl;


import java.util.List;

import javax.transaction.Transactional;

import com.seckill.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.seckill.model.entity.Orders;
import com.seckill.service.IOrderService;

/**
 *
 * @author kai
 * @date 2020-6-10 23:10
 */
@Service("orderService")
@Transactional(rollbackOn = Exception.class)
public class OrderServiceImpl implements IOrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Orders getOrderByUsernameAndCourseNo(String username, String courseNo) {
        return orderRepository.findByUsernameAndCourseNo(username, courseNo);
    }

    @Override
    public Orders saveAndFlush(Orders orders) {
        return orderRepository.saveAndFlush(orders);
    }

    @Override
    public List<Orders> findAllByUsername(String username, Sort sort) {
        return orderRepository.findByUsername(username, sort);
    }

}
