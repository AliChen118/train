package com.example.train.member.controller;

import com.example.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ali
 * @date 2023-07-16 08:30
 */

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("/count")
    public int count() {
        return memberService.count();
    }

    @PostMapping ("/register")
    public long register(String mobile) {
        return memberService.register(mobile);
    }
}
