package com.example.train.business.controller.admin;

import com.example.train.common.resp.CommonResp;
import com.example.train.common.resp.PageResp;
import com.example.train.business.req.TrainQueryReq;
import com.example.train.business.req.TrainSaveReq;
import com.example.train.business.resp.TrainQueryResp;
import com.example.train.business.service.TrainService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/train")
public class TrainAdminController {

    @Resource
    private TrainService trainService;

    @PostMapping ("/save")
    public CommonResp<Integer> save(@Valid @RequestBody TrainSaveReq req) {
        trainService.save(req);
        return new CommonResp<>();
    }

    @GetMapping ("/query-list")
    public CommonResp<PageResp<TrainQueryResp>> queryList(@Valid TrainQueryReq req) {
        PageResp<TrainQueryResp> pageResp = trainService.queryList(req);
        return new CommonResp<>(pageResp);
    }

    @DeleteMapping ("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        trainService.delete(id);
        return new CommonResp<>();
    }
}