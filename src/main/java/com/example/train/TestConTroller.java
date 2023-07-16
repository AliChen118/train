package com.example.train;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ali
 * @date 2023-07-16 08:30
 */

@RestController
public class TestConTroller {

    @GetMapping("/hello")
    public String hello() {
        return "Hello world123";
    }
}
