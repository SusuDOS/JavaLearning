package com.itheima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//为了让Spring和SpringMVC加载各自的类，限定SpringMVC加载bean的范围
//HttpMessageConverter接口,该接口是实现对象与JSON之间的自动转换工作:@EnableWebMvc
//在SpringMvcConfig中扫描SpringMvcSupport
@Configuration
@ComponentScan({"com.itheima.controller","com.itheima.config"})
@EnableWebMvc
public class SpringMvcConfig {
}
