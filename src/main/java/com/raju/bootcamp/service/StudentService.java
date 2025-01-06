package com.raju.bootcamp.service;

import com.raju.bootcamp.entity.StudentEntity;
import com.raju.bootcamp.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
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

        return studentRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Student not found with Id: "+id));

   }

    public  void deleteStudent(int id){
        if( ! studentRepository.existsById(id)){
            throw new EntityNotFoundException("Student not found with Id: "+id);
        }
        studentRepository.deleteById(id);
    }

    public StudentEntity updateStudent(int id, StudentEntity student){
        StudentEntity existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with Id: "+id));
        if (student.getName() !=null){
            existingStudent.setName(student.getName());
        }
        if (student.getEmail() !=null){
            existingStudent.setEmail(student.getEmail());
        }
        if (student.getCellNo() !=null){
            existingStudent.setCellNo(student.getCellNo());
        }

        return studentRepository.save(existingStudent);
    }

    public void updateStudent(StudentEntity student){
        studentRepository.save(student);
    }

    public StudentEntity findStudentByEmail(String email){
        return studentRepository.findByEmail(email)
                .orElseThrow(()->new EntityNotFoundException("Student not found with Email: "+email));
    }

}
