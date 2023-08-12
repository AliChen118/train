package com.example.train.business.service;

import com.example.train.business.domain.DailyTrainSeat;
import com.example.train.business.mapper.DailyTrainSeatMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Ali
 * @date 2023-08-12 17:30
 */

@Service
public class AfterConfirmOrderService {

    @Resource
    private DailyTrainSeatMapper dailyTrainSeatMapper;

    /**
     * 选中座位后事务处理：
     *      座位表修改售卖情况sell；
     *      余票详情表修改余票；
     *      为会员增加购票记录
     *      更新确认订单为成功
     */

    @Transactional
    public void afterDoConfirm(List<DailyTrainSeat> finalSeatList) {
        for (DailyTrainSeat dailyTrainSeat : finalSeatList) {
            DailyTrainSeat seatForUpdate = new DailyTrainSeat();
            seatForUpdate.setId(dailyTrainSeat.getId());
            seatForUpdate.setSell(dailyTrainSeat.getSell());
            seatForUpdate.setUpdateTime(new Date());
            dailyTrainSeatMapper.updateByPrimaryKeySelective(seatForUpdate);
        }
    }
}
