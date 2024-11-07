package com.art.apspb.dto;

import com.art.apspb.model.School;
import com.art.apspb.model.Student;


import java.util.List;


public record SchoolDTO(String name, Integer id, List<Integer> studentsId) {
    public static School toSchool(SchoolDTO schoolDTO){
        School school = new School();
        school.setName(schoolDTO.name);
        return school;
    }

    public static List<SchoolDTO> toSchoolResponse(List<School> schools){
        return schools.stream().map(school -> new SchoolDTO(school.getName(),
                        school.getId(),
                        school.getStudents()
                                .stream()
                                .map(Student::getId)
                                .toList()))
                .toList();
    }
}
