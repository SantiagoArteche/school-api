package com.art.apspb.controller;


import com.art.apspb.model.School;
import com.art.apspb.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService){
        this.schoolService = schoolService;
    }

    @GetMapping
    public ResponseEntity<List<School>> getSchools(){
        return ResponseEntity.status(200).body(this.schoolService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSchoolById(@PathVariable Integer id){
        School school = this.schoolService.getById(id);

        if(school == null){
            Map<String, String> notFound = new HashMap<>();
            notFound.put("Error", "School with id " + id + " not found");
            return ResponseEntity.status(404).body(notFound);
        }

        return ResponseEntity.status(200).body(school);
    }

    @PostMapping
    public ResponseEntity<School> createSchool(@RequestBody School school){
        this.schoolService.update(school);
        return ResponseEntity.status(201).body(school);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateSchool(@PathVariable Integer id, @RequestBody School school){
        School findSchool = this.schoolService.getById(id);
        Map<String, Object> response = new HashMap<>();
        int status;
        if(findSchool == null){
            response.put("Error", "School with id " + id + " not found");
            status = 404;
        }else{
            findSchool.setName(school.getName());
            this.schoolService.update(findSchool);
            response.put("Success", "School with id " + id + " was updated");
            response.put("School", findSchool);
            status = 200;
        }

        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteSchool(@PathVariable Integer id){
        School school = this.schoolService.getById(id);
        Map<String, String> response = new HashMap<>();
        int status;

        if(school == null){
            response.put("Error", "School with id " + id + " not found");
            status = 404;
        }else{
            this.schoolService.delete(id);
            response.put("Success", "School with id " + id + " was deleted");
            status = 200;
        }

        return ResponseEntity.status(status).body(response);
    }
}
