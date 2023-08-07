package com.example.train.business.controller.admin;

import com.example.train.common.resp.CommonResp;
import com.example.train.common.resp.PageResp;
import com.example.train.business.req.DailyTrainQueryReq;
import com.example.train.business.req.DailyTrainSaveReq;
import com.example.train.business.resp.DailyTrainQueryResp;
import com.example.train.business.service.DailyTrainService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/daily-train")
public class DailyTrainAdminController {

    @Resource
    private DailyTrainService dailyTrainService;

    @PostMapping ("/save")
    public CommonResp<Integer> save(@Valid @RequestBody DailyTrainSaveReq req) {
        dailyTrainService.save(req);
        return new CommonResp<>();
    }

    @GetMapping ("/query-list")
    public CommonResp<PageResp<DailyTrainQueryResp>> queryList(@Valid DailyTrainQueryReq req) {
        PageResp<DailyTrainQueryResp> pageResp = dailyTrainService.queryList(req);
        return new CommonResp<>(pageResp);
    }

    @DeleteMapping ("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        dailyTrainService.delete(id);
        return new CommonResp<>();
    }
}
