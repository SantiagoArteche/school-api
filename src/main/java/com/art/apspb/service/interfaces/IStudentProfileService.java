package com.art.apspb.service.interfaces;

import com.art.apspb.dto.StudentProfileDTO;
import com.art.apspb.model.StudentProfile;

import java.util.List;


public interface IStudentProfileService {
    List<StudentProfile> getAll();
    StudentProfile getById(Integer id);
    StudentProfile update(Integer id, StudentProfileDTO studentProfile);
    void save(StudentProfileDTO studentProfileDTO);
    boolean delete(Integer id);
}
