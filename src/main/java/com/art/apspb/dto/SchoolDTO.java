package com.art.apspb.dto;

import com.art.apspb.model.School;
import com.art.apspb.model.Student;

import java.util.List;
import java.util.stream.Collectors;

public record SchoolDTO(Integer id, String name) {
    public static School toSchool(SchoolDTO schoolDTO){
        School school = new School();
        school.setId(schoolDTO.id());
        school.setName(schoolDTO.name);
        return school;
    }
}
