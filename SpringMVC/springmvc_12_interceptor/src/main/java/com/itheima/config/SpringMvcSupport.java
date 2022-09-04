package com.itheima.config;

import com.itheima.controller.interceptor.ProjectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport {
    @Autowired
    private ProjectInterceptor projectInterceptor;

    // 添加访问静态资源路径
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
    }

    // 配置单拦截器在SpringMvcConfig需要设置为@ComponentScan({"com.itheima.controller","com.itheima.config"})即可生效单个
    // 不配置为@ComponentScan({"com.itheima.controller","com.itheima.config"})则SpringMvcConfig中的两个拦截器生效
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(projectInterceptor).addPathPatterns("/books", "/books/*");
    }
}
