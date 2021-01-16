package com.suyh323.service;

import com.suyh323.bean.Depart;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 这里添加了@RequestMapping() 注解，它的作用其实是做一个模糊映射，因为如果这里不添加这样一个模糊映射，
 * 那么springMVC就会将该类做为一个controller 控制器处理。
 * 而在DepartService 接口上也添加了一个@RequestMapping() 这样会导致相同的映射出现两份，最终启动失败。
 * 不过这里注解里面添加了这些映射地址可以是随意的，它没有实际的映射业务含义，只是为了解决映射报错问题。
 */
@Component
@RequestMapping("/fallback/consumer/depart")    // 这里的映射地址可以随意，没有实际业务含义，只要不冲突即可
public class DepartFallback implements DepartService {

    @Override
    public boolean saveDepart(Depart depart) {
        System.out.println("执行saveDepart()的服务降级方法 - class");
        return false;
    }

    @Override
    public boolean removeDepartById(Integer id) {
        System.out.println("执行removeDepartById()的服务降级方法 - class");
        return false;
    }

    @Override
    public boolean modifyDepart(Depart depart) {
        System.out.println("执行modifyDepart()的服务降级方法 - class");
        return false;
    }

    @Override
    public Depart getDepartById(Integer id) {
        System.out.println("执行getDepartById()的服务降级方法 - class");
        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("no this depart -- class");
        return depart;
    }

    @Override
    public List<Depart> listAllDeparts() {
        System.out.println("执行listAllDeparts()的服务降级方法 - class");
        return null;
    }
}
