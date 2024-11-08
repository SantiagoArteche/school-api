package com.art.apspb.controller;

import com.art.apspb.dto.StudentDTO;
import com.art.apspb.model.Student;
import com.art.apspb.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.OK).body(this.studentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStudentById(@PathVariable Integer id){
        Student student = this.studentService.getById(id);
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;

        if(student == null){
            response.put("Error", "Student with id " + id + " not found");
            status = HttpStatus.NOT_FOUND;
        }else{
            response.put("Student", student);
            status = HttpStatus.OK;
        }

        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<Student>> getStudentsByAge(@PathVariable Integer age){
        return ResponseEntity.status(HttpStatus.OK).body(this.studentService.getByAge(age));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Student>> getStudentsByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(this.studentService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createStudent(@Valid @RequestBody StudentDTO dto){
        Map<String, Object> response = new HashMap<>();
        this.studentService.save(dto);
        response.put("Success", "Student was created!");
        response.put("Student", dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStudent(@PathVariable Integer id, @Valid @RequestBody StudentDTO dto){
        Student updateStudent = this.studentService.update(id, dto);
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;

        if(updateStudent == null){
            response.put("Error", "Student with id " + id + " not found");
            status = HttpStatus.NOT_FOUND;
        }else{
            response.put("Success", "Student with id " + id + " was updated");
            response.put("Student", new StudentDTO(dto.name(), dto.age(), updateStudent.getSchool().getId()));
            status = HttpStatus.OK;
        }

        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteStudent(@PathVariable Integer id){
        boolean isDeleted = this.studentService.delete(id);
        Map<String, String> response = new HashMap<>();
        HttpStatus status;

        if(!isDeleted){
            response.put("Error", "Student with id " + id + " not found");
            status = HttpStatus.NOT_FOUND;
        }else{
            response.put("Success", "Student with id " + id + " was deleted");
            status = HttpStatus.OK;
        }

        return ResponseEntity.status(status).body(response);
    }
}
