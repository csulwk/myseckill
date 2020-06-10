package com.seckill.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.seckill.base.controller.BaseApiController;
import com.seckill.base.result.Result;
import com.seckill.base.result.ResultCode;
import com.seckill.model.User;
import com.seckill.service.IUserService;
import com.seckill.util.MD5Util;
import com.seckill.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class RegisterController extends BaseApiController {

    @Autowired
    public IUserService userService;

    /**
     * post-> submit the username and password
     * BindingResult, 记录验证结果
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result<User> register(@ModelAttribute(value = "user") @Valid User user, BindingResult bindingResult) {
        log.info("register...user:{}", JSONObject.toJSONString(user));
        /*
          验证用户名和密码有误的话，返回注册页面
         */
        if (bindingResult.hasErrors()) {
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }

        /*
          对用户的密码进行md5加密处理
         */
        String salt = UUIDUtil.randomNumber();
        log.info("password: {}, salt: {}", user.getPassword(), salt);
        String md5password = MD5Util.formToDatabase(user.getPassword(), salt);
        user.setDbflag(salt);
        user.setPassword(md5password);
        user.setId(2020);

        User newUser = userService.register(user);
        return Result.success(newUser);

    }


}
