package com.yqh.backend.repository;


import com.yqh.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 这个注解告诉Spring这是一个数据仓库Bean，虽然对于继承JpaRepository的接口不是必须的，但是个好习惯
public interface StudentRepository extends JpaRepository<Student, Long> {
    // 这个接口现在是空的，但它已经拥有了所有基础的CRUD魔法！
    // JpaRepository<Student, Long> 的意思是：
    // - 这是一个处理 Student 实体的 Repository。
    // - 这个实体的主键（@Id）的类型是 Long。

    // Spring Data JPA 会在运行时自动为我们提供以下方法：
    // - save(Student student): 保存或更新一个学生
    // - findById(Long id): 根据ID查找一个学生
    // - findAll(): 查找所有学生
    // - deleteById(Long id): 根据ID删除一个学生
    // - ...以及更多！
}