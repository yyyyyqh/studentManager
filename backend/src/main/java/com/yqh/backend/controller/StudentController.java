package com.yqh.backend.controller;


import com.yqh.backend.entity.Student;
import com.yqh.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
//@CrossOrigin(origins = "http://localhost:5173")
@RestController // 声明这是一个 RESTful 风格的控制器，它会直接将返回的对象转换为 JSON 格式
@RequestMapping("/api/students") // 定义这个控制器下所有 API 的公共路径前缀
public class StudentController {

    @Autowired // 自动注入 StudentService
    private StudentService studentService;

    // API 接口 1: 获取所有学生 (GET /api/students)
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // API 接口 2: 根据ID获取单个学生 (GET /api/students/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    // API 接口 3: 创建新学生 (POST /api/students)
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        // @RequestBody 注解表示请求的 body 体中的 JSON 数据将被自动转换成 Student 对象
        return studentService.createStudent(student);
    }

    // API 接口 4: 更新学生信息 (PUT /api/students/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }

    // API 接口 5: 删除学生 (DELETE /api/students/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        // 通常，删除操作成功后会返回一个 204 No Content 状态码，或者返回一个确认信息
        // 这里我们返回一个 JSON 对象来明确表示删除成功，方便前端处理
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}