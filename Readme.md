# Readme



云数据库 RDS MySQL 版：https://www.aliyun.com/product/rds/mysql?spm=5176.24416269.J_3207526240.201.21be4e9fusIq3e&scm=20140722.S_function@@product@@72296._.ID_function@@product@@72296-RL_rds-LOC_bar-OR_ser-V_2-P0_0

## 登录

### 短信验证码登录

<img src="https://ali-pic-bed.oss-cn-beijing.aliyuncs.com/image/202307210803341.jpg" alt="短信验证码登录1" style="zoom:25%;" />

<img src="https://ali-pic-bed.oss-cn-beijing.aliyuncs.com/image/202307210803137.jpg" alt="短信验证码登录2" style="zoom:25%;" />

<img src="https://ali-pic-bed.oss-cn-beijing.aliyuncs.com/image/202307210803468.jpg" alt="短信验证码登录3" style="zoom:25%;" />

### 单点登录

两种单点登录方案

1. redis + token
2. jwt √

![image-20230725222508966](https://ali-pic-bed.oss-cn-beijing.aliyuncs.com/image/202307252225011.png)

## 限流

限流 vs. 熔断：限流是做在被调用方的，熔断是做在调用方的

### 限流方法

例如：当前是第2.5秒

- 静态窗口限流：统计第2秒到现在的请求
- 动态窗口限流：统计第1.5秒到现在的请求

上述两种限流方法不能很好的应对突发的请求。使用下面的限流方法可以解决突发请求问题：

- 漏桶限流：请求进桶
- 令牌桶限流：令牌进桶
- 令牌大闸：如一旦产生了1000个令牌就不再产生令牌了

启动Sentinel控制台

```bash
java -Dserver.port=18080 -Dcsp.sentinel.dashboard.server=localhost:18080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.6.jar 
```



## 令牌大闸

### 引入原因

1. 防止机器人刷票
2. 加快余票查询，直接去查令牌余量更快

## MQ

### RocketMQ

### 将购票流程一分为二

1. 同步流程：验证码校验、令牌
2. 异步流程：选座购票

### 增加排队购票功能

### 购票时序图

<img src="https://ali-pic-bed.oss-cn-beijing.aliyuncs.com/image/202308230941803.png" alt="image-20230823094139755" style="zoom:150%;" />

排队出票，拿到令牌就拿到了购票的资格，避免出现拿到令牌却因拿不到锁而无法购票的情况。
