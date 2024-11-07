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
    public ResponseEntity<List<SchoolDTO>> getSchools(){
        return ResponseEntity.status(HttpStatus.OK).body(this.schoolService.getAll().stream().map(school ->new SchoolDTO(school.getId(),school.getName())
        ).toList());
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
    public ResponseEntity<SchoolDTO> createSchool(@RequestBody SchoolDTO dto){
        School school = SchoolDTO.toSchool(dto);
        this.schoolService.update(school);
        SchoolDTO schoolDTO = new SchoolDTO(school.getId(), dto.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(schoolDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateSchool(@PathVariable Integer id, @RequestBody School school){
        School findSchool = this.schoolService.getById(id);
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;
        if(findSchool == null){
            response.put("Error", "School with id " + id + " not found");
            status = HttpStatus.NOT_FOUND;
        }else{
            findSchool.setName(school.getName());
            this.schoolService.update(findSchool);
            response.put("Success", "School with id " + id + " was updated");
            response.put("School", findSchool);
            status = HttpStatus.OK;
        }

        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteSchool(@PathVariable Integer id){
        School school = this.schoolService.getById(id);
        Map<String, String> response = new HashMap<>();
        HttpStatus status;

        if(school == null){
            response.put("Error", "School with id " + id + " not found");
            status = HttpStatus.NOT_FOUND;
        }else{
            this.schoolService.delete(id);
            response.put("Success", "School with id " + id + " was deleted");
            status = HttpStatus.OK;
        }

        return ResponseEntity.status(status).body(response);
    }
}
