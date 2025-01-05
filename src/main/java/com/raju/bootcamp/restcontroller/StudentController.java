package com.raju.bootcamp.restcontroller;

import com.raju.bootcamp.entity.StudentEntity;
import com.raju.bootcamp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("/")
    public ResponseEntity<List<StudentEntity>> getAllStudents(){

        List<StudentEntity> studentList = studentService.getAllStudents();

        return ResponseEntity.ok(studentList);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveStudent(@RequestBody StudentEntity student){
        studentService.saveStudent(student);

        return new ResponseEntity<>("Student save successfully", HttpStatus.CREATED);
    }


}
