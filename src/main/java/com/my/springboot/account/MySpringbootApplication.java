package com.my.springboot.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
//@MapperScan(value = "com.my.springboot.account.repo")
public class MySpringbootApplication {

    public static void main(String[] args) {
        //SpringApplication.run(MySpringbootApplication.class, args);
        new SpringApplicationBuilder(MySpringbootApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
