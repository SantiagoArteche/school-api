package com.art.apspb.dto;

import com.art.apspb.model.School;
import com.art.apspb.model.Student;

import java.util.List;

public record SchoolResponseDTO ( String name,
                                  Integer id,
                                  List<Integer> studentsId){
    public static List<SchoolResponseDTO> toSchoolResponse(List<School> schools){
        return schools.stream().map(school -> new SchoolResponseDTO(school.getName(),
                        school.getId(),
                        school.getStudents()
                                .stream()
                                .map(Student::getId)
                                .toList()))
                .toList();
    }
}
