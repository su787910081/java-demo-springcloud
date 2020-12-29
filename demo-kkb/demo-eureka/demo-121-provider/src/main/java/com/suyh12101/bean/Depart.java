package com.suyh12101.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity  // 使用自动建表
// HttpMessageConverter   Jackson -> 完成Java对象与JSON数据间的转换工作
// JPA的默认实现是Hibernate，而Hibernate默认对于对象的查询是基于延迟加载的
// Depart depart = service.findById(5);   这里的depart实际是一个javasist动态代理(字节码动态代理)对象
// 上面的这一句虽然调用了去查询数据库的操作，但是实际它并没有去做这个动作，而是返回一个动态代理对象。
// 只有在访问这个动态代码对象的详情时，才会真正的去数据库中做查询操作。比如下面的这句操作代码。
// String name = depart.getName();  // 访问动态代理对象的详情
// 所以使用下面这个注解忽略掉这些json属性
// 这里的意思是，如果直接从数据库中查询出来，然后返回的结果我们不做任何访问操作就直接返回给WEB 前端的话，
// 这个时候会进行序列化，而以这时这个代理对象实际上是没有任何实际数据的，序列化就会报错，抛出异常。
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Depart {
    @Id  // 表示当前属性为自动建的表的主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 主键自动递增
    private Integer id;
    private String name;
}
