package com.example.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.train.business.domain.TrainCarriage;
import com.example.train.business.domain.TrainSeat;
import com.example.train.business.domain.TrainSeatExample;
import com.example.train.business.enums.SeatColEnum;
import com.example.train.business.mapper.TrainSeatMapper;
import com.example.train.business.req.TrainSeatQueryReq;
import com.example.train.business.req.TrainSeatSaveReq;
import com.example.train.business.resp.TrainSeatQueryResp;
import com.example.train.common.resp.PageResp;
import com.example.train.common.util.SnowUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TrainSeatService {
    private static final Logger LOG = LoggerFactory.getLogger(TrainSeatService.class);

    @Resource
    private TrainSeatMapper trainSeatMapper;
    
    @Resource
    private TrainCarriageService trainCarriageService;

    public void save(TrainSeatSaveReq req) {
        DateTime now = new DateTime();
        TrainSeat trainSeat = BeanUtil.copyProperties(req, TrainSeat.class);
        if (ObjectUtil.isNull(trainSeat.getId())) {
            trainSeat.setId(SnowUtil.getSnowflakeNextId());
            trainSeat.setCreateTime(now);
            trainSeat.setUpdateTime(now);
            trainSeatMapper.insert(trainSeat);
        } else {
            trainSeat.setUpdateTime(now);
            trainSeatMapper.updateByPrimaryKey(trainSeat);
        }
    }

    public PageResp<TrainSeatQueryResp> queryList(TrainSeatQueryReq req) {
        TrainSeatExample trainSeatExample = new TrainSeatExample();
        trainSeatExample.setOrderByClause("train_code asc, carriage_index asc, carriage_seat_index asc");
        TrainSeatExample.Criteria criteria = trainSeatExample.createCriteria();
        if (ObjectUtil.isNotEmpty(req.getTrainCode())) {
            criteria.andTrainCodeEqualTo(req.getTrainCode());
        }

        LOG.info("查询页码: {}", req.getPage());
        LOG.info("每页条数: {}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<TrainSeat> trainSeatList = trainSeatMapper.selectByExample(trainSeatExample);
        PageInfo<TrainSeat> pageInfo = new PageInfo<>(trainSeatList);
        LOG.info("总行数: {}", pageInfo.getTotal());
        LOG.info("总页数: {}", pageInfo.getPages());

        List<TrainSeatQueryResp> list = BeanUtil.copyToList(trainSeatList, TrainSeatQueryResp.class);

        PageResp<TrainSeatQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    @Transactional
    public void delete(Long id) {
        trainSeatMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量生成火车座位
     * @param trainCode
     * 批量生成需要考虑是否支持重复生成：
     *      1. 存在就跳过
     *      2. 先删除已有的再生成 √
     * 批量生成数据还得好绿的问题：【事务】
     */
    public void genTrainSeat(String trainCode) {
        DateTime now = DateTime.now();
        // 1. 清空当前火车下的所有座位
        TrainSeatExample trainSeatExample = new TrainSeatExample();
        TrainSeatExample.Criteria criteria = trainSeatExample.createCriteria();
        criteria.andTrainCodeEqualTo(trainCode);
        trainSeatMapper.deleteByExample(trainSeatExample);
        // 2. 查询当前火车下的所有车厢
        List<TrainCarriage> carriageList = trainCarriageService.selectByTrainCode(trainCode);
        LOG.info("当前车次下的车厢数: {}", carriageList.size());

        // 3. 循环生成每个车厢的座位
        for (TrainCarriage trainCarriage : carriageList) {
            // 3.1 拿到车厢数据，行数和座位类型(列数)
            Integer rowCount = trainCarriage.getRowCount();
            String seatType = trainCarriage.getSeatType();
            int seatIndex = 1;

            // 3.2 根据车厢类型，筛选出所有列，比如车厢类型是一等座，则筛选出columnList
            List<SeatColEnum> colEnumList = SeatColEnum.getColsByType(seatType);
            LOG.info("根据车厢的座位类型，筛选出所有列: {}", colEnumList);

            // 3.3 循环行数 - 循环列数
            for (int row = 1; row <= rowCount ; row++) {
                for (SeatColEnum seatColEnum : colEnumList) {
                    TrainSeat trainSeat = new TrainSeat();
                    trainSeat.setId(SnowUtil.getSnowflakeNextId());
                    trainSeat.setTrainCode(trainCode);
                    trainSeat.setCarriageIndex(trainCarriage.getIndex());
                    trainSeat.setRow(StrUtil.fillBefore(String.valueOf(row), '0', 2));
                    trainSeat.setCol(seatColEnum.getCode());
                    trainSeat.setSeatType(seatType);
                    trainSeat.setCarriageSeatIndex(seatIndex++);
                    trainSeat.setCreateTime(now);
                    trainSeat.setUpdateTime(now);

                    trainSeatMapper.insert(trainSeat);
                    
                }

            }
        }
    }

    public List<TrainSeat> selectByTrainCode(String trainCode) {
        TrainSeatExample trainSeatExample = new TrainSeatExample();
        trainSeatExample.setOrderByClause("`id` asc");
        TrainSeatExample.Criteria criteria = trainSeatExample.createCriteria();
        criteria.andTrainCodeEqualTo(trainCode);
        return trainSeatMapper.selectByExample(trainSeatExample);
    }
}
