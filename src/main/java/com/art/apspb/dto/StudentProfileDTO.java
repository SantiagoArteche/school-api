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
    public static StudentProfile toStudentProfile(StudentProfileDTO studentProfileDTO){
        StudentProfile studentProfile = new StudentProfile();
        if(studentProfileDTO.bio != null){
            studentProfile.setBio(studentProfileDTO.bio);
        }
        if(studentProfileDTO.studentId != null){
            Student student = new Student();
            student.setId(studentProfileDTO.studentId);
            studentProfile.setStudent(student);
        }
        return studentProfile;
    }
}
