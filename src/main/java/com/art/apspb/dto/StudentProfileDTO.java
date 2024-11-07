package com.art.apspb.dto;

import com.art.apspb.model.Student;
import com.art.apspb.model.StudentProfile;

public record StudentProfileDTO(String bio, Integer studentId) {
    public static StudentProfile toStudentProfile(StudentProfileDTO studentProfileDTO){
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setBio(studentProfileDTO.bio);
        Student student = new Student();
        student.setId(studentProfileDTO.studentId);
        studentProfile.setStudent(student);
        return studentProfile;
    }
}
