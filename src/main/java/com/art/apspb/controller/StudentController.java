package com.art.apspb.controller;


import com.art.apspb.model.Student;
import com.art.apspb.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(200).body(this.studentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable Integer id){
        Student student = this.studentService.getById(id);

        if(student == null){
            Map<String, String> notFound = new HashMap<>();
            notFound.put("Error", "Student with id " + id + " not found");
            return ResponseEntity.status(404).body(notFound);
        }

        return ResponseEntity.status(200).body(student);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<Student>> getStudentsByAge(@PathVariable Integer age){
        List<Student> student = this.studentService.getByAge(age);

        return ResponseEntity.status(200).body(student);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Student>> getStudentsByName(@PathVariable String name){
        List<Student> student = this.studentService.getByName(name);

        return ResponseEntity.status(200).body(student);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        this.studentService.update(student);
        return ResponseEntity.status(201).body(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStudent(@PathVariable Integer id, @RequestBody Student student){
        Student findStudent = this.studentService.getById(id);
        Map<String, Object> response = new HashMap<>();
        int status;
        if(findStudent == null){
            response.put("Error", "Student with id " + id + " not found");
            status = 404;
        }else{
            findStudent.setAge(student.getAge());
            findStudent.setName(student.getName());
            this.studentService.update(findStudent);
            response.put("Success", "Student with id " + id + " was updated");
            response.put("Student", findStudent);
            status = 200;
        }

        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteStudent(@PathVariable Integer id){
        Student student = this.studentService.getById(id);
        Map<String, String> response = new HashMap<>();
        int status;

        if(student == null){
            response.put("Error", "Student with id " + id + " not found");
            status = 404;
        }else{
            this.studentService.delete(id);
            response.put("Success", "Student with id " + id + " was deleted");
            status = 200;
        }

        return ResponseEntity.status(status).body(response);
    }
}
