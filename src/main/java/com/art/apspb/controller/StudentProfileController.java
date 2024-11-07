package com.art.apspb.controller;

import com.art.apspb.dto.StudentProfileDTO;

import com.art.apspb.model.StudentProfile;
import com.art.apspb.service.StudentProfileService;

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
    public ResponseEntity<Map<String, Object>> getStudentProfileById(@PathVariable Integer id){
        StudentProfile findStudentProfile = this.studentProfileService.getById(id);
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;

        if(findStudentProfile == null){
            response.put("Error", "Student profile with id " + id + " not found");
            status = HttpStatus.NOT_FOUND;
        }else{
            response.put("Student Profile", findStudentProfile);
            status = HttpStatus.OK;
        }

        return ResponseEntity.status(status).body(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createStudentProfile(@RequestBody StudentProfileDTO dto){
        Map<String, Object> response = new HashMap<>();

        try{
            this.studentProfileService.save(dto);
            response.put("Success", "Student Profile was created!");
            response.put("Student", dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception ex){
            response.put("Error", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStudentProfile(@PathVariable Integer id, @RequestBody StudentProfileDTO dto){
        StudentProfile studentProfile = this.studentProfileService.update(id, dto);
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;

        if(studentProfile == null){
            response.put("Error", "Student profile with id " + id + " not found");
            status = HttpStatus.NOT_FOUND;
        }else{
            response.put("Success", "Student profile with id " + id + " was updated");
            response.put("Student Profile", studentProfile);
            status = HttpStatus.OK;
        }
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteStudentProfile(@PathVariable Integer id){
        boolean isDeleted = this.studentProfileService.delete(id);
        Map<String, String> response = new HashMap<>();
        HttpStatus status;

        if(!isDeleted){
            response.put("Error", "Student profile with id " + id + " not found");
            status = HttpStatus.NOT_FOUND;
        }else{
            response.put("Success", "Student profile with id " + id + " was deleted");
            status = HttpStatus.OK;
        }

        return ResponseEntity.status(status).body(response);
    }
}
