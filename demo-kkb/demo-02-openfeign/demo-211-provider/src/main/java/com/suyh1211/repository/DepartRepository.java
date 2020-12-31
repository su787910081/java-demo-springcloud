package com.suyh1211.repository;

import com.suyh1211.bean.Depart;
import org.springframework.data.jpa.repository.JpaRepository;

// 第一个泛型是，当前Repository所操作的对象的类型
// 第二个泛型是，当前Repository所操作的对象的id类型
public interface DepartRepository extends JpaRepository<Depart, Integer> {
}
