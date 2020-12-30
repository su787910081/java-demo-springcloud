

> eureka官网：https://github.com/Netflix/eureka/



![img](eureka.jpg)



`@EnableEurekaClient`: 它只能去访问Eureka 注册中心

`@EnableDiscoveryClient`: 它可以访问任何的注册中心(包括eureka)

这两个注解是在SpringBoot 应用的时候需要添加这两个注解，如果是SpringCloud应用则不需要添加如上两个注解就可访问eureka注册中心。

