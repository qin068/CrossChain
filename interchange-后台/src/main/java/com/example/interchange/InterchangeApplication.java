package com.example.interchange;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.interchange.mapper")
public class InterchangeApplication {
    public static void main(String[] args) {
        SpringApplication.run(InterchangeApplication.class, args);
    }

}
