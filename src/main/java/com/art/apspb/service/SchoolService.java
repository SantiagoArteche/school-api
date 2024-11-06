package com.art.apspb.service;

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
    public void update(School school) {
        this.schoolRepository.save(school);
    }

    @Override
    public void delete(Integer id) {
        this.schoolRepository.deleteById(id);
    }
}
