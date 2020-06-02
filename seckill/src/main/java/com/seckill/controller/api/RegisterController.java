package com.seckill.controller.api;

import com.seckill.base.result.Result;
import com.seckill.base.result.ResultCode;
import com.seckill.model.User;
import com.seckill.service.IUserService;
import com.seckill.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class RegisterController {

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

        /*
          验证用户名和密码有误的话，返回注册页面
         */
        if (bindingResult.hasErrors()) {
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }

        /*
          对用户的密码进行md5加密处理
         */
        log.info("Password: {}", user.getPassword());
        String md5password = MD5Util.formToDB(user.getPassword(), "kk");
        user.setDbflag("kk");
        user.setPassword(md5password);
        user.setId(2018);

        User newUser = userService.register(user);
        return Result.success(newUser);

    }


}
