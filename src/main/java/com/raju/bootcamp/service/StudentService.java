package com.raju.bootcamp.service;

import com.raju.bootcamp.entity.StudentEntity;
import com.raju.bootcamp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public List<StudentEntity> getAllStudents(){
        return studentRepository.findAll();
    }

   public void saveStudent(StudentEntity student){
        studentRepository.save(student);
   }

   public StudentEntity getStudentById(int id){
        return studentRepository.getById(id);
   }



}
