package com.suyh.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.suyh.entities.Product;
import com.suyh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: 梦学谷
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Product product) {
        return productService.add(product);
    }

    // fallbackMethod 指定当get 方法出现异常时将要调用的处理方法
    // 处理方法的返回值和参数列表要一致
    @HystrixCommand(fallbackMethod = "getFallback")
    @RequestMapping(value = "/product/get/{id}", method = RequestMethod.GET)
    public Product get(@PathVariable("id") Long id) {
        Product product = productService.get(id);

        // 如果为空，模拟一个异常
        if (product == null) {
            throw new RuntimeException("ID: " + id + ", 无效");
        }

        return product;
    }

    @RequestMapping(value = "/product/list", method = RequestMethod.GET)
    public List<Product> list() {
        return productService.list();
    }

    public Product getFallback(@PathVariable("id") Long id) {
        return new Product(id, "ID=" + id + ", 无效----@HystrixCommand",
                "无法找到对应数据库");
    }
}
