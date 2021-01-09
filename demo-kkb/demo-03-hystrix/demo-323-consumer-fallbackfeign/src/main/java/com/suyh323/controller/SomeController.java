package com.suyh323.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.suyh323.bean.Depart;
import com.suyh323.service.DepartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/consumer/depart")
public class SomeController {

    @Autowired
    private DepartService service;

    @PostMapping("/save")
    public boolean saveHandler(@RequestBody Depart depart) {
        return service.saveDepart(depart);
    }

    @DeleteMapping("/del/{id}")
    public boolean deleteHandler(@PathVariable("id") int id) {
        return service.removeDepartById(id);
    }

    @PutMapping("/update")
    public boolean updateHandler(@RequestBody Depart depart) {
        return service.modifyDepart(depart);
    }

    // 指定该方法要使用服务降级。即当前处理器方法在运行过程中若发生异常，
    // 无法给客户端正常响应时，就会调用fallbackMethod指定的方法
    @GetMapping("/get/{id}")
    public Depart getByIdHandler(@PathVariable("id") int id) {
        log.info("getByIdHandler, current thread name: {}", Thread.currentThread().getName());
        return getByIdHandlerMiddle(id);
    }

    @HystrixCommand(fallbackMethod = "getHystrixHandler",
            commandProperties = @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000"))
    public Depart getByIdHandlerMiddle(@PathVariable("id") int id) {
        log.info("getByIdHandlerMiddle, current thread name: {}", Thread.currentThread().getName());
        return service.getDepartById(id);
    }

    // 定义服务降级方法，即响应给客户端的备选方案
    public Depart getHystrixHandler(@PathVariable("id") int id) {
        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("no this depart -- method");
        return depart;
    }

    @GetMapping("/list")
    public List<Depart> listHandler() {
        return service.listAllDeparts();
    }
}
