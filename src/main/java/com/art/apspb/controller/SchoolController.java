package com.art.apspb.controller;

import com.art.apspb.dto.SchoolDTO;
import com.art.apspb.dto.SchoolResponseDTO;
import com.art.apspb.model.School;
import com.art.apspb.service.SchoolService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<SchoolResponseDTO>> getSchools(){
        List<School> schools = this.schoolService.getAll();
        List<SchoolResponseDTO> schoolResponseDto = SchoolResponseDTO.toSchoolResponse(schools);
        return ResponseEntity.status(HttpStatus.OK).body(schoolResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getSchoolById(@PathVariable Integer id){
        School school = this.schoolService.getById(id);
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;

        if(school == null){
            response.put("Error", "School with id " + id + " not found");
            status = HttpStatus.NOT_FOUND;
        }else{
            response.put("School", school);
            status = HttpStatus.OK;
        }

        return ResponseEntity.status(status).body(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createSchool(@Valid @RequestBody SchoolDTO dto) {
        Map<String, Object> response = new HashMap<>();

        this.schoolService.save(dto);
        response.put("Success", "School was created!");
        response.put("School", dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateSchool(@PathVariable Integer id, @Valid @RequestBody SchoolDTO dto){
        School updateSchool = this.schoolService.update(id, dto);
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;

        if(updateSchool == null){
            response.put("Error", "School with id " + id + " not found");
            status = HttpStatus.NOT_FOUND;
        }else{
            response.put("Success", "School with id " + id + " was updated");
            response.put("School", new SchoolDTO(dto.name()));
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
