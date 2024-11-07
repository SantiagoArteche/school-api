package com.art.apspb.service;

import com.art.apspb.dto.SchoolDTO;
import com.art.apspb.dto.StudentDTO;
import com.art.apspb.model.School;
import com.art.apspb.model.Student;
import com.art.apspb.repository.SchoolRepository;
import com.art.apspb.service.interfaces.ISchoolService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService implements ISchoolService {

    SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository){
        this.schoolRepository = schoolRepository;
    }

    @Override
    public List<School> getAll() {
        return this.schoolRepository.findAll();
    }

    @Override
    public School getById(Integer id) {
        return this.schoolRepository.findById(id).orElse(null);
    }

    @Override
    public School update(Integer id, SchoolDTO dto) {
        School findSchool = this.schoolRepository.findById(id).orElse(null);

        if(findSchool != null){
            School school = SchoolDTO.toSchool(dto);
            school.setId(findSchool.getId());
            this.schoolRepository.save(school);
        }

        return findSchool;
    }

    @Override
    public void save(SchoolDTO dto) {
        School school = SchoolDTO.toSchool(dto);
        this.schoolRepository.save(school);
    }

    @Override
    public boolean delete(Integer id) {
        School findSchool = this.schoolRepository.findById(id).orElse(null);
        if(findSchool != null){
            this.schoolRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
