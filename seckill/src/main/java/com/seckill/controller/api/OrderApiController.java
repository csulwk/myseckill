package com.seckill.controller.api;

import com.seckill.controller.BaseApiController;
import com.seckill.model.Result;
import com.seckill.annotation.CurrentUser;
import com.seckill.model.entity.Orders;
import com.seckill.model.entity.User;
import com.seckill.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class OrderApiController extends BaseApiController {

    @Autowired
    IOrderService orderService;

    @RequestMapping(value = "orderList", method = RequestMethod.GET)
    public Result<List<Orders>> orderList(@CurrentUser User user) {
        //按照订单时间的创建时间的降序来排列
        List<Orders> ordersList = orderService.findAllByUsername(user.getUsername(),
                new Sort(Sort.Direction.DESC,"createDate"));
        return Result.success(ordersList);

    }
 }
