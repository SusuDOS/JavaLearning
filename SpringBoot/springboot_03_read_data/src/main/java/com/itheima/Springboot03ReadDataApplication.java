package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot03ReadDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot03ReadDataApplication.class, args);
    }
}




/*多环境配置，使用具体某一环境方法：
java -java spring*.jav --spring.profiles.active=test
java -java spring*.jav --server.port=88 --spring.profiles.active=test*/
