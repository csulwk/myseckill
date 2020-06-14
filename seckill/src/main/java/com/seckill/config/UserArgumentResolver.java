package com.seckill.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.seckill.model.User;
import com.seckill.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * CurrentUser自定义注解的解析器：用于Controller注入用户信息，
 * 实现两个方法，当supportsParameter返回true时，才会调用resolveArgument方法。
 * @author kai
 * @date 2020-6-14 22:36
 */
@Configuration
@Slf4j
public class UserArgumentResolver implements HandlerMethodArgumentResolver{

	@Autowired
	public IUserService userService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(User.class)
                && methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

	@Override
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
		HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String requestToken = request.getParameter("token");
		String cookieToken = getTokenFromCookie(request);
		if(requestToken == null && cookieToken == null){
			return null;
		}
        Object user = userService.getUserFromRedisByToken((requestToken != null ? requestToken : cookieToken));
		log.info("UserArgumentResolver: requestToken={}, cookieToken={}, user={}", requestToken, cookieToken, JSONObject.toJSONString(user));
		return user;
	}

    /**
     * 从 Cookie 信息中获取token值
     * @param request HttpServletRequest
     * @return token
     */
    private String getTokenFromCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie ck : cookies){
            if("token".equals(ck.getName())){
                return ck.getValue();
            }
        }
        return null;
    }
}
