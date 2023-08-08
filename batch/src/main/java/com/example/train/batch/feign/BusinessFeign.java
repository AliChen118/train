package com.example.train.batch.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ali
 * @date 2023-08-08 08:04
 */

@FeignClient(name="business", url="http://127.0.0.1:8002/business")
public interface BusinessFeign {

    @GetMapping("/hello")
    String hello1();
}
