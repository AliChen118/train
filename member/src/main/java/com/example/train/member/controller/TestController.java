package com.example.train.member.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ali
 * @date 2023-08-14 07:50
 */

@RestController
@RefreshScope
public class TestController {

    @Value("${test.nacos}")
    private String testNacos;

    @GetMapping("/hello")
    public String hello() {
        return String.format("Hello %s!", testNacos);
    }
}
