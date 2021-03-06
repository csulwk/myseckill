package com.seckill.config;

import java.util.List;

import com.seckill.handler.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 将 UserArgumentResolver 加入到 WebMvcConfigurerAdapter 中
 * @author kai
 * @date 2020-6-14 22:33
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport{

	@Autowired
    UserArgumentResolver userArgumentResolver;

	@Override
	protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
		argumentResolvers.add(userArgumentResolver);
	}

	@Override
    public void addResourceHandlers (ResourceHandlerRegistry registry){
		registry.addResourceHandler("/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
		super.addResourceHandlers(registry);
	}
}
