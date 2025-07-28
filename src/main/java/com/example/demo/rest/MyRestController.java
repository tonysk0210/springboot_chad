package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    //set field name equals the value of name defined in application.properties
    @Value("${name}")
    private String name;

    @GetMapping("/")
    public String home() {
        return "welcome to the home page";
    }

    @GetMapping("/name")
    public String name() {
        return name;
    }
}
