package com.gexiao.course;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.gexiao.course.filter.CourseFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = "com.gexiao.course")
@EnableDubbo
public class CourseEdgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseEdgeServiceApplication.class, args);
    }


    @Bean
    public FilterRegistrationBean filterRegistrationBean(CourseFilter courseFilter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(courseFilter);

        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));

        return filterRegistrationBean;
    }
}
