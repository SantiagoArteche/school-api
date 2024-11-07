package com.art.apspb.controller;


import com.art.apspb.dto.StudentDTO;
import com.art.apspb.model.Student;
import com.art.apspb.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        return ResponseEntity.status(HttpStatus.OK).body(this.studentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable Integer id){
        Student student = this.studentService.getById(id);

        if(student == null){
            Map<String, String> notFound = new HashMap<>();
            notFound.put("Error", "Student with id " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
        }

        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<Student>> getStudentsByAge(@PathVariable Integer age){
        List<Student> student = this.studentService.getByAge(age);

        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Student>> getStudentsByName(@PathVariable String name){
        List<Student> student = this.studentService.getByName(name);

        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createStudent(@RequestBody StudentDTO dto){
        Map<String, Object> response = new HashMap<>();

        try{
            Student student = StudentDTO.toStudent(dto);
            this.studentService.update(student);
            response.put("Success", "Student was created!");
            response.put("Student", student);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception ex){
            response.put("Error", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStudent(@PathVariable Integer id, @RequestBody Student student){
        Student findStudent = this.studentService.getById(id);
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;

        if(findStudent == null){
            response.put("Error", "Student with id " + id + " not found");
            status = HttpStatus.NOT_FOUND;
        }else{
            findStudent.setAge(student.getAge());
            findStudent.setName(student.getName());
            this.studentService.update(findStudent);
            response.put("Success", "Student with id " + id + " was updated");
            response.put("Student", findStudent);
            status = HttpStatus.OK;
        }

        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteStudent(@PathVariable Integer id){
        Student student = this.studentService.getById(id);
        Map<String, String> response = new HashMap<>();
        HttpStatus status;

        if(student == null){
            response.put("Error", "Student with id " + id + " not found");
            status = HttpStatus.NOT_FOUND;
        }else{
            this.studentService.delete(id);
            response.put("Success", "Student with id " + id + " was deleted");
            status = HttpStatus.OK;
        }

        return ResponseEntity.status(status).body(response);
    }
}
