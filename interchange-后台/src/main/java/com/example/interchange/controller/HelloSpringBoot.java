package com.example.interchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 秃头小刘
 * @date 2021-09-15 1:19 PM
 */

@RestController
public class HelloSpringBoot {

    @RequestMapping("/hello")
    public String hello(){
        return "helloword";
    }

//    @PostMapping("/test")
    @GetMapping("/test")
    //    @RequestMapping("/test")
    public List<String> test(){
        List<String> list = new ArrayList<>();
        list.add("123");
        return list;
    }

}
