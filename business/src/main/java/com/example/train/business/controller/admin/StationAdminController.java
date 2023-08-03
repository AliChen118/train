package com.example.train.business.controller.admin;

import com.example.train.common.resp.CommonResp;
import com.example.train.common.resp.PageResp;
import com.example.train.business.req.StationQueryReq;
import com.example.train.business.req.StationSaveReq;
import com.example.train.business.resp.StationQueryResp;
import com.example.train.business.service.StationService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/station")
public class StationAdminController {

    @Resource
    private StationService stationService;

    @PostMapping ("/save")
    public CommonResp<Integer> save(@Valid @RequestBody StationSaveReq req) {
        stationService.save(req);
        return new CommonResp<>();
    }

    @GetMapping ("/query-list")
    public CommonResp<PageResp<StationQueryResp>> queryList(@Valid StationQueryReq req) {
        PageResp<StationQueryResp> pageResp = stationService.queryList(req);
        return new CommonResp<>(pageResp);
    }

    @GetMapping ("/query-all")
    public CommonResp<List<StationQueryResp>> queryAll() {
        List<StationQueryResp> list = stationService.queryAll();
        return new CommonResp<>(list);
    }

    @DeleteMapping ("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        stationService.delete(id);
        return new CommonResp<>();
    }
}
