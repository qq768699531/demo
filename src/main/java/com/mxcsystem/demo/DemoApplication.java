package com.mxcsystem.demo;

import com.mxcsystem.demo.util.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DemoApplication {
    public static void main (String[] args) {
        //SpringApplication.run(DemoApplication.class, args);
        ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        SpringUtil.setApplicationContext(applicationContext);
    }
}
