package com.example;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.StudentMapper;
import com.example.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*", "null"})
public class Controller {
    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/students")
    public String getStudents() {
        List<Student> students = studentMapper.selectList(null);
        return JSON.toJSONString(students);
    }

    @PostMapping("/add")
    public void addStudents(@RequestBody Student student) {
        studentMapper.insert(student);
    }

    @PostMapping("/delete")
    public void delStudents(@RequestBody Student student) {
        studentMapper.deleteById(student);
    }

    @PostMapping("/update")
    public void updateStudents(@RequestBody Student student) {
        studentMapper.updateById(student);
    }

    @PostMapping("/search")
    public String searchStudents(@RequestBody HashMap<String, String> data) {
        String name = data.get("name");
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.like("name", name);
        List<Student> students = studentMapper.selectList(studentQueryWrapper);
        return JSON.toJSONString(students);
    }

}
