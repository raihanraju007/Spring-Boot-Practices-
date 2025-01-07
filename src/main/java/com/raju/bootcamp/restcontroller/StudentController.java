package com.raju.bootcamp.restcontroller;

import com.raju.bootcamp.entity.StudentEntity;
import com.raju.bootcamp.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<String> saveStudent(
            @RequestPart StudentEntity student,
            @RequestParam(value = "image",required = true)MultipartFile file) throws IOException {
        studentService.saveStudent(student,file);

        return new ResponseEntity<>("Student save successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id){
        try{
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Student with ID "+id+ " has been deleted");
        }
        catch (EntityNotFoundException message){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message.getMessage());
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable int id,@RequestBody StudentEntity student){
        StudentEntity updateStudent = studentService.updateStudent(id,student);

        return ResponseEntity.ok(updateStudent);
    }

    @GetMapping("/{email}")
    public ResponseEntity<StudentEntity> findStudentByEmail(@PathVariable String email){
        StudentEntity updateStudent = studentService.findStudentByEmail(email);

        return ResponseEntity.ok(updateStudent);
    }

}
