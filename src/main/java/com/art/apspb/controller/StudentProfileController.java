package com.art.apspb.controller;


import com.art.apspb.dto.StudentDTO;
import com.art.apspb.dto.StudentProfileDTO;
import com.art.apspb.model.Student;
import com.art.apspb.model.StudentProfile;
import com.art.apspb.service.StudentProfileService;
import com.art.apspb.service.StudentService;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.OK).body(this.studentProfileService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentProfileById(@PathVariable Integer id){
        StudentProfile studentProfile = this.studentProfileService.getById(id);

        if(studentProfile == null){
            Map<String, String> notFound = new HashMap<>();
            notFound.put("Error", "Student profile with id " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
        }

        return ResponseEntity.status(HttpStatus.OK).body(studentProfile);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createStudentProfile(@RequestBody StudentProfileDTO dto){
        Map<String, Object> response = new HashMap<>();
        try{
            StudentProfile student = StudentProfileDTO.toStudentProfile(dto);
            this.studentProfileService.update(student);
            response.put("Success", "Student Profile was created!");
            response.put("Student", student);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception ex){
            response.put("Error", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStudentProfile(@PathVariable Integer id, @RequestBody StudentProfile studentProfile){
        StudentProfile findStudentProfile = this.studentProfileService.getById(id);
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;
        if(findStudentProfile == null){
            response.put("Error", "Student profile with id " + id + " not found");
            status = HttpStatus.NOT_FOUND;
        }else{
            findStudentProfile.setBio(studentProfile.getBio());
            this.studentProfileService.update(findStudentProfile);
            response.put("Success", "Student profile with id " + id + " was updated");
            response.put("Student Profile", findStudentProfile);
            status = HttpStatus.OK;
        }

        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteStudentProfile(@PathVariable Integer id){
        StudentProfile school = this.studentProfileService.getById(id);
        Map<String, String> response = new HashMap<>();
        HttpStatus status;

        if(school == null){
            response.put("Error", "Student profile with id " + id + " not found");
            status = HttpStatus.NOT_FOUND;
        }else{
            this.studentProfileService.delete(id);
            response.put("Success", "Student profile with id " + id + " was deleted");
            status = HttpStatus.OK;
        }

        return ResponseEntity.status(status).body(response);
    }
}
