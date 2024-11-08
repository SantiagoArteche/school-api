package com.art.apspb.dto;

import com.art.apspb.model.School;
import com.art.apspb.model.Student;

import java.util.List;

public record SchoolResponseDTO ( Integer id,
                                  String name,
                                  List<Integer> studentsId){
    public static List<SchoolResponseDTO> toSchoolResponse(List<School> schools){
        if(schools == null){
            throw new NullPointerException("The list of schools is null");
        }

        return schools.stream().map(school -> new SchoolResponseDTO(school.getId(),
                        school.getName(),
                        school.getStudents()
                                .stream()
                                .map(Student::getId)
                                .toList()))
                .toList();
    }
}
