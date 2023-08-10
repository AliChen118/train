package com.example.train.business.controller.admin;

import com.example.train.common.resp.CommonResp;
import com.example.train.common.resp.PageResp;
import com.example.train.business.req.DailyTrainTicketQueryReq;
import com.example.train.business.req.DailyTrainTicketSaveReq;
import com.example.train.business.resp.DailyTrainTicketQueryResp;
import com.example.train.business.service.DailyTrainTicketService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/daily-train-ticket")
public class DailyTrainTicketAdminController {

    @Resource
    private DailyTrainTicketService dailyTrainTicketService;

    @PostMapping ("/save")
    public CommonResp<Integer> save(@Valid @RequestBody DailyTrainTicketSaveReq req) {
        dailyTrainTicketService.save(req);
        return new CommonResp<>();
    }

    @GetMapping ("/query-list")
    public CommonResp<PageResp<DailyTrainTicketQueryResp>> queryList(@Valid DailyTrainTicketQueryReq req) {
        PageResp<DailyTrainTicketQueryResp> pageResp = dailyTrainTicketService.queryList(req);
        return new CommonResp<>(pageResp);
    }

    @DeleteMapping ("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        dailyTrainTicketService.delete(id);
        return new CommonResp<>();
    }
}