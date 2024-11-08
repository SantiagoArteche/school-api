package com.art.apspb.dto;

import com.art.apspb.model.Student;
import com.art.apspb.model.StudentProfile;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record StudentProfileDTO(
        @NotEmpty(message = "Bio is required")
        String bio,
        @NotNull(message = "Student id is required")
        Integer studentId
) {
    public static StudentProfile toStudentProfile(StudentProfileDTO dto){
        if(dto == null){
            throw new NullPointerException("The student profile DTO is null");
        }

        StudentProfile studentProfile = new StudentProfile();
        if(dto.bio != null){
            studentProfile.setBio(dto.bio);
        }
        if(dto.studentId != null){
            Student student = new Student();
            student.setId(dto.studentId);
            studentProfile.setStudent(student);
        }
        return studentProfile;
    }
}
