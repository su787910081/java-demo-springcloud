

> eureka官网：https://github.com/Netflix/eureka/



![img](eureka.jpg)



`@EnableEurekaClient`: 它只能去访问Eureka 注册中心

`@EnableDiscoveryClient`: 它可以访问任何的注册中心(包括eureka)

这两个注解是在SpringBoot 应用的时候需要添加这两个注解，如果是SpringCloud应用则不需要添加如上两个注解就可访问eureka注册中心。



> Eureka Server 的自我保护机制

- 清理Down 掉的微服务

  Eureka Server 有一个定时任务，会定时检查有哪些微服务处理down 状态，然后检查这些down 状态的微服务是否满足踢掉的条件，如果满足条件则将其踢下线，不会再出现在Eureka Server 的注册管理表中。

  这个定时任务的默认时间为60 秒。即每分钟会清理一批。

  

  > 修改Eureka 相关配置，以便能更快感知并踢除down掉的微服务

  ```properties
  # 注意：该配置是配置在Eureka Server 端
  
  # 可通过该配置修改定时清理任务的周期时间，单位毫秒
  eureka.server.eviction-interval-timer-in-ms = 60000
  
  ```

  ```properties
  # 注意：该配置是配置在Eureka Client 端
  
  # 设置当前Client向Server发送一次心跳的时间间隔，单位秒
  eureka.instance.lease-renewal-interval-in-seconds = 1
  # 指定让Server认定当前Client已经失效的时间，将来可以从注册表中剔除了，单位秒
  eureka.instance.lease-expiration-duration-in-seconds = 3
  ```

  > Eureka Client的相关配置

  ```java
  // 在一个配置接口中定义
  com.netflix.discovery.EurekaClientConfig;
  // 然后SpringCloud 和Netflix 都有自己的默认实现
  com.netflix.discovery.DefaultEurekaClientConfig;
  org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
  ```

  > Eureka Server的相关配置

  ```java
  // 接口
  com.netflix.eureka.EurekaServerConfig;
  ```

  > Eureka Instance 的相关配置

  ```java
  com.netflix.appinfo.EurekaInstanceConfig;
  
  org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
  ```

  > GUI 上的属性

  <img src="自我保护机制01.png" alt="自我保护机制01" style="zoom: 25%;" />

|                          |                           |
| ------------------------ | ------------------------- |
| Current time             | 2020-12-31T23:05:11 +0800 |
| Uptime                   | 00:17                     |
| Lease expiration enabled | true                      |
| Renews threshold         | 0                         |
| Renews (last min)        | 0                         |

- `Renews threshold` 

  > `EurekaServer`期望的每分钟收到`EurekaClient`的续约(心跳)的数量
  >
  > 该值是一个瞬间值，从该时刻进行计算所得到。
  >
  > 从当前时刻往前推15分钟(该值可通过配置项进行修改: eureka.server.renewal-threshold-update-interval-ms)
  >
  > `期望值 = 心跳数量 * ${eureka.server.renewal-percent-threshold}(默认阈值: 0.85) / ${eureka.server.renewal-threshold-update-interval-ms}(默认时间: 15分钟)`
  >
  > 这里应该是总的心跳数量，估计。

- `Renews (last min)`

  > `Eureka Server`实际在最后一分钟收到客户端的续约数量。

- 说明

  > 若 `Renews (last min)` < `Renews threshold` 时启用自我保护机制

### 服务离线

- 服务下架

  > 将注册到EurekaServer中的EurekaClient 从Server 的注册表中移除，这样其他Client就无法发现该Client了

- 服务下线

  > Client并没有从EurekaServer的注册表中移除(其他Client仍可发现该服务)，而是通过修改服务的状态来达到其它Client无法调用的目标。