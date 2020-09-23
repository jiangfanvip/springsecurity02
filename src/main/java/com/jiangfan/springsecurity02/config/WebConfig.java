package com.jiangfan.springsecurity02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * ClassName: WebConfig
 * Description:
 * date: 2020-8-21 1:03
 *
 * @author LENOVO
 * @since JDK 1.8
 */
@Configuration //相当于springmvc.xml
public class WebConfig implements WebMvcConfigurer {

    /**
     * 访问/默认为应用根目录，重定向到spring security 的/login登录页面
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/").setViewName("redirect:/login");
        registry.addViewController("/").setViewName("redirect:/login-view");
        registry.addViewController("/login-view").setViewName("login");


    }

}
