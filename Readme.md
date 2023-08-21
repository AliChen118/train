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