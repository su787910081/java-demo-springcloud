# 多维请求限流

spring-cloud-zuul-ratelimit 库

提供多种细粒度的限流策略。

- user

  > 针对用户的限流，对单位时间窗内经过网关的用户数量的限制。

- origin

  > 针对客户端IP的限流，对单位时间窗内经过网关的IP数量的限制。

- url

  > 针对请求URL的限流，对单位时间窗内经过网关的URL数量的限制。

使用时，我们只需要添加依赖，然后修改配置文件就可以了。