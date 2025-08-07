package com.yqh.backend.entity;


import jakarta.persistence.*;

@Entity // 告诉 Spring Data JPA: 这是一个需要持久化到数据库的实体类
@Table(name = "student") // 指定这个类对应数据库中的哪张表
public class Student {

    @Id // 声明这是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 设置主键的生成策略为自增
    private Long id;

    @Column(name = "name", nullable = false) // 映射到表中的 name 列，且不允许为空
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "department")
    private String department;

    // --- Getters and Setters ---
    // 你可以使用 IDE 自动生成它们 (在代码区域右键 -> Generate -> Getters and Setters)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}