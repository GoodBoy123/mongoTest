package com.example.mongodb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/sayHello")
    public String sayHello()
    {
        return "nice to meet you";
    }
}
