package com.art.apspb.controller;


import com.art.apspb.dto.SchoolDTO;
import com.art.apspb.model.School;
import com.art.apspb.service.SchoolService;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.OK).body(this.schoolService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSchoolById(@PathVariable Integer id){
        School school = this.schoolService.getById(id);

        if(school == null){
            Map<String, String> notFound = new HashMap<>();
            notFound.put("Error", "School with id " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
        }

        return ResponseEntity.status(HttpStatus.OK).body(school);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createSchool(@RequestBody SchoolDTO dto){
        Map<String, Object> response = new HashMap<>();
        try{
            this.schoolService.save(dto);
            response.put("Success", "School was created!");
            response.put("School", dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception ex){
            response.put("Error", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateSchool(@PathVariable Integer id, @RequestBody SchoolDTO dto){
        School updateSchool = this.schoolService.update(id, dto);
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;

        if(updateSchool == null){
            response.put("Error", "School with id " + id + " not found");
            status = HttpStatus.NOT_FOUND;
        }else{
            response.put("Success", "School with id " + id + " was updated");
            response.put("School", dto);
            status = HttpStatus.OK;
        }
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteSchool(@PathVariable Integer id){
        boolean isDeleted = this.schoolService.delete(id);
        Map<String, String> response = new HashMap<>();
        HttpStatus status;

        if(!isDeleted){
            response.put("Error", "School with id " + id + " not found");
            status = HttpStatus.NOT_FOUND;
        }else{
            response.put("Success", "School with id " + id + " was deleted");
            status = HttpStatus.OK;
        }

        return ResponseEntity.status(status).body(response);
    }
}
