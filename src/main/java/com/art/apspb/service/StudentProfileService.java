package com.art.apspb.service;

import com.art.apspb.dto.StudentProfileDTO;
import com.art.apspb.model.Student;
import com.art.apspb.model.StudentProfile;
import com.art.apspb.repository.StudentProfileRepository;
import com.art.apspb.repository.StudentRepository;
import com.art.apspb.service.interfaces.IStudentProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileService implements IStudentProfileService {
    private final StudentProfileRepository studentProfileRepository;
    private final StudentRepository studentRepository;

    public StudentProfileService(StudentProfileRepository studentProfileRepository, StudentRepository studentRepository){
        this.studentProfileRepository = studentProfileRepository;
        this.studentRepository = studentRepository;
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
            if(studentProfile.getBio() == null){
                studentProfile.setBio(findStudentProfile.getBio());
            }

            if(studentProfile.getStudent() == null){
                if(findStudentProfile.getStudent() != null){
                    studentProfile.setStudent(findStudentProfile.getStudent());
                }
            }else{
                Student findStudent = this.studentRepository.findById(studentProfile.getStudent().getId()).orElse(null);
                if(dto.studentId() != null && findStudent == null){
                    findStudentProfile.setId(-1);
                    return findStudentProfile;
                }
            }

            this.studentProfileRepository.save(studentProfile);
        }
        return findStudentProfile;
    }

    @Override
    public boolean delete(Integer id) {
        StudentProfile findStudentProfile = this.studentProfileRepository.findById(id).orElse(null);

        if(findStudentProfile != null){
            if(findStudentProfile.getStudent().getId() != null){
                this.studentRepository.deleteById(findStudentProfile.getStudent().getId());
            }
            this.studentProfileRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
