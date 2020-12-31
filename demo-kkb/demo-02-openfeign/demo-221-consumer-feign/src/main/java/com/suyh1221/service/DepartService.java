package com.suyh1221.service;

import com.suyh1221.bean.Depart;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// 关于Feign的说明：
// 1)Feign接口名一般是与业务接口名相同的，但不是必须的
// 2)Feign接口中的方法名一般也是与业务接口方法名相同，但也不是必须的
// 3)Feign接口中的方法返回值类型，方法参数要求与业务接口中的相同
// 4)接口上与方法上的Mapping的参数URI要与提供者处理器相应方法上的Mapping的URI相同

// 指定当前为Feign客户端，参数为提供者的微服务名称
@FeignClient("abcmsc-provider-depart")
@RequestMapping("/provider/depart")
public interface DepartService {
    @PostMapping("/save")
    boolean saveDepart(@RequestBody Depart depart);

    @DeleteMapping("/del/{id}")
    boolean removeDepartById(@PathVariable("id") Integer id);

    @PutMapping("/update")
    boolean modifyDepart(@RequestBody Depart depart);

    @GetMapping("/get/{id}")
    Depart getDepartById(@PathVariable("id") Integer id);

    @GetMapping("/list")
    List<Depart> listAllDeparts();
}
