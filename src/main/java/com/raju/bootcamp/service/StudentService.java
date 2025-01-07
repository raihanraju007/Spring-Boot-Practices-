package com.raju.bootcamp.service;

import com.raju.bootcamp.entity.StudentEntity;
import com.raju.bootcamp.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Value("src/main/resources/static/images")
    private String uploadDir;


    public List<StudentEntity> getAllStudents(){
        return studentRepository.findAll();
    }


   public void saveStudent(StudentEntity student, MultipartFile imageFile) throws IOException {

        if (imageFile!=null && !imageFile.isEmpty()){
            String imageFileName = saveImage(imageFile,student);
            student.setImage(imageFileName);
        }

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

    private String saveImage(MultipartFile file, StudentEntity student) throws IOException {
        Path uploadPath = Paths.get(uploadDir+"/students");
        if(! Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        String fileName = student.getName()+"_"+ UUID.randomUUID().toString();
        Path filePath=uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(),filePath);

        return  fileName;
    }


}
