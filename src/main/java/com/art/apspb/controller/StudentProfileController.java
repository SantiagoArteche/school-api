package com.art.apspb.controller;


import com.art.apspb.model.StudentProfile;
import com.art.apspb.service.StudentProfileService;
import com.art.apspb.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students-profiles")
public class StudentProfileController {
    private final StudentProfileService studentProfileService;

    public StudentProfileController(StudentProfileService studentProfileService){
        this.studentProfileService = studentProfileService;
    }

    @GetMapping
    public ResponseEntity<List<StudentProfile>> getStudentProfiles(){
        return ResponseEntity.status(200).body(this.studentProfileService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentProfileById(@PathVariable Integer id){
        StudentProfile studentProfile = this.studentProfileService.getById(id);

        if(studentProfile == null){
            Map<String, String> notFound = new HashMap<>();
            notFound.put("Error", "Student profile with id " + id + " not found");
            return ResponseEntity.status(404).body(notFound);
        }

        return ResponseEntity.status(200).body(studentProfile);
    }

    @PostMapping
    public ResponseEntity<StudentProfile> createStudentProfile(@RequestBody StudentProfile studentProfile){
        this.studentProfileService.update(studentProfile);
        return ResponseEntity.status(201).body(studentProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStudentProfile(@PathVariable Integer id, @RequestBody StudentProfile studentProfile){
        StudentProfile findStudentProfile = this.studentProfileService.getById(id);
        Map<String, Object> response = new HashMap<>();
        int status;
        if(findStudentProfile == null){
            response.put("Error", "Student profile with id " + id + " not found");
            status = 404;
        }else{
            findStudentProfile.setBio(studentProfile.getBio());
            this.studentProfileService.update(findStudentProfile);
            response.put("Success", "Student profile with id " + id + " was updated");
            response.put("Student Profile", findStudentProfile);
            status = 200;
        }

        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteStudentProfile(@PathVariable Integer id){
        StudentProfile school = this.studentProfileService.getById(id);
        Map<String, String> response = new HashMap<>();
        int status;

        if(school == null){
            response.put("Error", "Student profile with id " + id + " not found");
            status = 404;
        }else{
            this.studentProfileService.delete(id);
            response.put("Success", "Student profile with id " + id + " was deleted");
            status = 200;
        }

        return ResponseEntity.status(status).body(response);
    }
}
