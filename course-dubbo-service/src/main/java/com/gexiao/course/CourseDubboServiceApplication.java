package com.gexiao.course;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class CourseDubboServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseDubboServiceApplication.class, args);
    }

}
