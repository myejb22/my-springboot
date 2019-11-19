package com.my.springboot.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MySpringbootApplication {

    public static void main(String[] args) {
        //SpringApplication.run(MySpringbootApplication.class, args);
        new SpringApplicationBuilder(MySpringbootApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
