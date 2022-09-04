package com.itheima.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

//web容器配置类
public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer {
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext acm = new AnnotationConfigWebApplicationContext();
        acm.register(SpringMvcConfig.class);
        return acm;
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
