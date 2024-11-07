package com.art.apspb.dto;

import com.art.apspb.model.School;

public record SchoolDTO(String name) {
    public static School toSchool(SchoolDTO schoolDTO){
        School school = new School();
        school.setName(schoolDTO.name);
        return school;
    }
}
