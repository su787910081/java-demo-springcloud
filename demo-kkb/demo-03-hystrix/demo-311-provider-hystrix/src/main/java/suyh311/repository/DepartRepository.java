package suyh311.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suyh311.bean.Depart;

// 第一个泛型是，当前Repository所操作的对象的类型
// 第二个泛型是，当前Repository所操作的对象的id类型
public interface DepartRepository extends JpaRepository<Depart, Integer> {
}
