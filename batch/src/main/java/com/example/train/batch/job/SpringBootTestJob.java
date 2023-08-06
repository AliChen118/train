package com.example.train.batch.job;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Ali
 * @date 2023-08-06 18:28
 */

/**
 * 定时任务三大要素：
 *      1. 执行的内容：功能要素
 *      2. 执行的策略：cron表达式
 *      3. 开关：开启定时任务
 *  SpringBoot自带的定时任务只适合单体应用，不适合集群
 *  可以通过增加分布式锁，解决集群问题，但没法实时更改任务状态和策略
 */

@Component
@EnableScheduling
public class SpringBootTestJob {

    @Scheduled(cron = "0/5 * * * * ?")
    private void test() {
        System.out.println("SpringBootTestJob Test!");
    }
}
