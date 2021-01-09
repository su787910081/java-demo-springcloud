package com.suyh.service;

import com.suyh.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

// 指定调用微服务名称
@FeignClient(value = "microservice-product",
        // 所有方法级映射要使用的前缀。
        // 不要在@FeignClient 注解的类上面添加 @RequestMapping 注解，这个注解会让springMVC认为此接口也是一个controller 控制 器
        // 最终导致启动失败，所以在这里添加上下文前缀，以及映射前缀
        path = "/product",
        fallback = ProductClientServiceFallback.class)
public interface ProductClientService {

    // 因为@FeignClient 注解中添加了 path="/product" 所以这里的访问路径是: /product/add
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    // @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    boolean add(@RequestBody Product product);

    // 因为@FeignClient 注解中添加了 path="/product" 所以这里的访问路径是: /product/get/{id}
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    // @RequestMapping(value = "/product/get/{id}", method = RequestMethod.GET)
    Product get(@PathVariable("id") Long id);

    // 因为@FeignClient 注解中添加了 path="/product" 所以这里的访问路径是: /product/list
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    // @RequestMapping(value = "/product/list", method = RequestMethod.GET)
    List<Product> list();

}
