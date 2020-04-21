package com.mdl.zhaopin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReusmeParseApplication {

    public static void main(String[] args) {

        System.setProperty("spring.config.location","classpath:/configs.properties");

        SpringApplication.run(ReusmeParseApplication.class, args);
    }

}
