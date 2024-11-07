package com.art.apspb.service;

import com.art.apspb.dto.StudentProfileDTO;
import com.art.apspb.model.StudentProfile;
import com.art.apspb.repository.StudentProfileRepository;
import com.art.apspb.service.interfaces.IStudentProfileService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentProfileService implements IStudentProfileService {
    private final StudentProfileRepository studentProfileRepository;

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
    public void save(StudentProfileDTO dto){
        StudentProfile studentProfile = StudentProfileDTO.toStudentProfile(dto);
        this.studentProfileRepository.save(studentProfile);
    }

    @Override
    public StudentProfile update(Integer id, StudentProfileDTO dto) {
        StudentProfile findStudentProfile = this.studentProfileRepository.findById(id).orElse(null);
        if(findStudentProfile != null){
            StudentProfile studentProfile = StudentProfileDTO.toStudentProfile(dto);
            studentProfile.setId(findStudentProfile.getId());
            this.studentProfileRepository.save(studentProfile);
        }
        return findStudentProfile;
    }

    @Override
    public boolean delete(Integer id) {
        StudentProfile findStudentProfile = this.studentProfileRepository.findById(id).orElse(null);
        if(findStudentProfile != null){
            this.studentProfileRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
