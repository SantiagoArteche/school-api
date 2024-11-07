package com.art.apspb.dto;

import com.art.apspb.model.School;
import jakarta.validation.constraints.NotEmpty;

public record SchoolDTO(
        @NotEmpty(message = "Name is required")
        String name) {
    public static School toSchool(SchoolDTO schoolDTO){
        School school = new School();
        school.setName(schoolDTO.name);
        return school;
    }
}
