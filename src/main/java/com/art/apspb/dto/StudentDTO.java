package com.art.apspb.dto;

import com.art.apspb.model.School;
import com.art.apspb.model.Student;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record StudentDTO(
        @NotEmpty(message = "Name is required")
        String name,

        @NotNull(message = "Age is required")
        @Min(value = 1, message = "The min age is 1!")
        Integer age,

        Integer schoolId
) {
    public static Student toStudent(StudentDTO dto){
        Student student = new Student();
        student.setAge(dto.age);
        student.setName(dto.name);

        School school = new School();

        if(dto.schoolId != null){
            school.setId(dto.schoolId());
            student.setSchool(school);
        }

        return student;
    }
}
