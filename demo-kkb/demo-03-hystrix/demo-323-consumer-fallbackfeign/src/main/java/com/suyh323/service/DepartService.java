package com.suyh323.service;

import com.suyh323.bean.Depart;
import org.springframework.cloud.openfeign.FeignClient;
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
// fallback用于指定当前Feign接口的服务降级类

/**
 * 这里@FeignClient() 注解中有两个属性都可以指定服务降级类，不过这两个属性所指定的服务降级类是有所不同的。
 * fallback属性: 它指定的服务降级类是实现当前接口的一个实现类
 * fallbackFactory属性: 它指定的服务降级类是需要实现 FallbackFactory 这个接口的实现类
 */
@FeignClient(value = "abcmsc-provider-depart", fallback = DepartFallback.class, path = "/suyh")
@RequestMapping("/provider/depart")
public interface DepartService {
    @PostMapping("/save")
    boolean saveDepart(@RequestBody Depart depart);

    @DeleteMapping("/del/{id}")
    boolean removeDepartById(@PathVariable("id") Integer id);

    @PutMapping("/update")
    boolean modifyDepart(@RequestBody Depart depart);

    @GetMapping("/provider/depart/get/{id}")
    Depart getDepartById(@PathVariable("id") Integer id);

    @GetMapping("/list")
    List<Depart> listAllDeparts();
}
