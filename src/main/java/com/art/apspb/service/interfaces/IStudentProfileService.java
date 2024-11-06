package com.art.apspb.service.interfaces;

import com.art.apspb.model.School;
import com.art.apspb.model.StudentProfile;

import java.util.List;

public interface IStudentProfileService {
    List<StudentProfile> getAll();
    StudentProfile getById(Integer id);
    void update(StudentProfile studentProfile);
    void delete(Integer id);
}
