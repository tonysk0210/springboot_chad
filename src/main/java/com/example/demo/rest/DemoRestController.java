package com.example.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoRestController {

    //add code for "/hello" endpoint
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
