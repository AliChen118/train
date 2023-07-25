package com.example.train.member.controller;

import com.example.train.common.resp.CommonResp;
import com.example.train.member.req.MemberLogineReq;
import com.example.train.member.req.MemberRegisterReq;
import com.example.train.member.req.MemberSendCodeReq;
import com.example.train.member.resp.MemberLoginResp;
import com.example.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
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
    public CommonResp<Integer> count() {
        int count = memberService.count();
        return new CommonResp<>(count);
    }

    @PostMapping ("/register")
    public CommonResp<Long> register(@Valid @RequestBody MemberRegisterReq req) {
        long id = memberService.register(req);
        return new CommonResp<>(id);
    }

    @PostMapping ("/send-code")
    public CommonResp<Long> sendCode(@Valid @RequestBody MemberSendCodeReq req) {
        memberService.sendCode(req);
        return new CommonResp<>();
    }

    @PostMapping ("/login")
    public CommonResp<MemberLoginResp> login(@Valid @RequestBody MemberLogineReq req) {
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<>(resp);
    }
}
