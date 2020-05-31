package com.seckill.controller;

import com.seckill.model.User;
import com.seckill.service.IUserService;
import com.seckill.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class RegisterController {
    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    public IUserService userService;



    /**
     * get , jump to the register page
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public ModelAndView toRegister(ModelMap modelMap) {
        User user = new User();

        return new ModelAndView("register").addObject(user);
    }

    /**
     * post-> submit the username and password
     * blindingresult, 记录验证结果
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute(value = "user") @Valid  User user, BindingResult bindingResult) {


        /**
         * 验证用户名和密码有误的话，返回注册页面
         */
        if (bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }


        /**
         * 对用户的密码进行md5加密处理
         */
        logger.info("--------" + user.getPassword());
        String md5password = MD5Util.formToDB(user.getPassword(), "kk");
        user.setDbflag("kk");
        user.setPassword(md5password);
        user.setId(2018);

        User newUser = userService.register(user);

//        if (newUser == null) {
//            return new ModelAndView("home");
//
//        }else {
//            return new ModelAndView("register");
//        }
//        return new ModelAndView("register");
        return new ModelAndView("login");

    }


}
