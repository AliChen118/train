package com.example.train.member.controller;

import com.example.train.common.context.LoginMemberContext;
import com.example.train.common.resp.CommonResp;
import com.example.train.common.resp.PageResp;
import com.example.train.member.req.PassengerQueryReq;
import com.example.train.member.req.PassengerSaveReq;
import com.example.train.member.resp.PassengerQueryResp;
import com.example.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ali
 * @date 2023-07-16 08:30
 */

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerService;

    @PostMapping ("/save")
    public CommonResp<Integer> save(@Valid @RequestBody PassengerSaveReq req) {
        passengerService.save(req);
        return new CommonResp<>();
    }

    @GetMapping ("/query-list")
    public CommonResp<PageResp<PassengerQueryResp>> queryList(@Valid PassengerQueryReq req) {
        req.setMemberId(LoginMemberContext.getId());
        PageResp<PassengerQueryResp> pageResp = passengerService.queryList(req);
        return new CommonResp<>(pageResp);
    }
}
