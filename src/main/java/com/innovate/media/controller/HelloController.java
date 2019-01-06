package com.innovate.media.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Peng
 * Time: 2016/12/16 15:45
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String say(){
        return "Hello SpringBoot!";
    }
}
