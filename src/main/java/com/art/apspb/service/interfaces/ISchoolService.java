package com.art.apspb.service.interfaces;

import com.art.apspb.model.School;
import com.art.apspb.model.Student;

import java.util.List;

public interface ISchoolService {
    List<School> getAll();
    School getById(Integer id);
    void update(School school);
    void delete(Integer id);
}
