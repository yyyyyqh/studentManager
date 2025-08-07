package com.yqh.backend.service;


import com.yqh.backend.entity.Student;
//异常处理
import com.yqh.backend.exception.ResourceNotFoundException;
//数据持久层，继承会具有一些方法可使用
import com.yqh.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 告诉 Spring 这是一个服务类 (Service Bean)，需要由 Spring 容器来管理
public class StudentService {

    @Autowired // 自动注入：Spring会自动找到 StudentRepository 的实例并赋值给这个变量
    private StudentRepository studentRepository;

    // 业务方法：获取所有学生
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 业务方法：根据ID获取单个学生
    public Student getStudentById(Long id) {
        // findById返回一个Optional，如果找不到，我们就抛出自定义的异常
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    // 业务方法：创建新学生
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // 业务方法：更新学生信息
    public Student updateStudent(Long id, Student studentDetails) {
        // 首先，根据ID从数据库里把现有的学生信息查出来
        Student student = getStudentById(id); // 复用上面的 getStudentById 方法，找不到会自动抛异常

        // 然后，用传入的新信息(studentDetails)来更新查出来的学生对象
        student.setName(studentDetails.getName());
        student.setGender(studentDetails.getGender());
        student.setAge(studentDetails.getAge());
        student.setDepartment(studentDetails.getDepartment());

        // 最后，把更新后的学生对象存回数据库
        return studentRepository.save(student);
    }

    // 业务方法：删除学生
    public void deleteStudent(Long id) {
        // 先检查一下学生是否存在，不存在的话 getStudentById 会抛出异常
        Student student = getStudentById(id);

        // 如果存在，就执行删除
        studentRepository.delete(student);
    }
}