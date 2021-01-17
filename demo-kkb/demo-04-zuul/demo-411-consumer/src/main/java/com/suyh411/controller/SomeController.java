package com.suyh411.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.suyh411.bean.Depart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/consumer/depart")
public class SomeController {
    @Autowired
    private RestTemplate restTemplate;
    // 直连提供者
    // private static final String SERVICE_PROVIDER = "http://localhost:8081";
    // 要使用微服务名称来从eureka server查找提供者
    private static final String SERVICE_PROVIDER = "http://abcmsc-provider-depart";
    @PostMapping("/save")
    public boolean saveHandler(@RequestBody Depart depart) {

        String url = SERVICE_PROVIDER + "/provider/depart/save";
        return restTemplate.postForObject(url, depart, Boolean.class);
    }

    @DeleteMapping("/del/{id}")
    public void deleteHandler(@PathVariable("id") int id) {
        String url = SERVICE_PROVIDER + "/provider/depart/del/" + id;
        restTemplate.delete(url);
    }

    @PutMapping("/update")
    public void updateHandler(@RequestBody Depart depart) {
        String url = SERVICE_PROVIDER + "/provider/depart/update";
        restTemplate.put(url, depart);
    }

    @HystrixCommand(fallbackMethod = "getHystrixHandler")
    @GetMapping("/get/{id}")
    public Depart getByIdHandler(@PathVariable("id") int id, HttpServletRequest request) {
        String url = SERVICE_PROVIDER + "/provider/depart/get/" + id;
        return restTemplate.getForObject(url, Depart.class);
    }

    public Depart getHystrixHandler(@PathVariable("id") int id, HttpServletRequest request) {
        System.out.println("token = " + request.getHeader("token"));
        System.out.println("Cookie = " + request.getHeader("Cookie"));
        System.out.println("Set-Cookie = " + request.getHeader("Set-Cookie"));

        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("no this depart - 8080");
        return depart;
    }

    @GetMapping("/list")
    public List<Depart> listHandler() {
        String url = SERVICE_PROVIDER + "/provider/depart/list";
        return restTemplate.getForObject(url, List.class);
    }
}
