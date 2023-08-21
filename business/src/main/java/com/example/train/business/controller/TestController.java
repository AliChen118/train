package com.example.train.business.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.train.business.service.TestService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Resource
    private TestService testService;

    @SentinelResource("hello")
    @GetMapping("/hello")
    public String hello() throws InterruptedException {
//        int i = RandomUtil.randomInt(1, 10);
//        if (i <= 1) {
//            throw new RuntimeException("测试异常");
//        }
        return "Hello World! Business!";
    }

    @SentinelResource("hello1")
    @GetMapping("/hello1")
    public String hello1() throws InterruptedException {
        testService.hello2();
        return "Hello World! Business1!";
    }
}
