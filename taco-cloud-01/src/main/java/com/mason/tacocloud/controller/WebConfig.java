package com.mason.tacocloud.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Mason
 * @Description 如果一个控制器很简单，不需要填充模型或处理数据，那么
 *              可以用另外一种方式定义控制器。那就是实现WebMvcConfigurer接口
 *              我们可以使用它注册一个或多个试图控制器。在这里我们可以替代HomeController控制器
 * @date 2022/6/29 22:46
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("home");
    }


}
