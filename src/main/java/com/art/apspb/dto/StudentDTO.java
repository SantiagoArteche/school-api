package com.art.apspb.dto;

import com.art.apspb.model.School;
import com.art.apspb.model.Student;

public record StudentDTO(String name, Integer schoolId, Integer age) {
    public static Student toStudent(StudentDTO dto){
        Student student = new Student();
        student.setName(dto.name);
        student.setAge(dto.age);
        School school = new School();

        if(dto.schoolId() != null){
            school.setId(dto.schoolId());
            student.setSchool(school);
        }

        return student;
    }
}
