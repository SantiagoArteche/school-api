package com.art.apspb.dto;

import com.art.apspb.model.School;
import jakarta.validation.constraints.NotEmpty;

public record SchoolDTO(
        @NotEmpty(message = "Name is required")
        String name
) {
    public static School toSchool(SchoolDTO dto){
        if(dto == null){
            throw new NullPointerException("The school DTO is null");
        }

        School school = new School();
        school.setName(dto.name);
        return school;
    }
}
