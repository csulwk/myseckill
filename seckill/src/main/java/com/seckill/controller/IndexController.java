package com.seckill.controller;

import com.seckill.model.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/")
    public Result<String> home(){
        return Result.success("home");

    }
}
