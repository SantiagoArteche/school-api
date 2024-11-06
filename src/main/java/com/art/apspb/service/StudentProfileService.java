package com.art.apspb.service;

import com.art.apspb.model.StudentProfile;
import com.art.apspb.repository.StudentProfileRepository;
import com.art.apspb.service.interfaces.IStudentProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileService implements IStudentProfileService {
    StudentProfileRepository studentProfileRepository;

    public StudentProfileService(StudentProfileRepository studentProfileRepository){
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public List<StudentProfile> getAll() {
        return this.studentProfileRepository.findAll();
    }

    @Override
    public StudentProfile getById(Integer id) {
        return this.studentProfileRepository.findById(id).orElse(null);
    }

    @Override
    public void update(StudentProfile studentProfile) {
        this.studentProfileRepository.save(studentProfile);
    }

    @Override
    public void delete(Integer id) {
        this.studentProfileRepository.deleteById(id);
    }
}
