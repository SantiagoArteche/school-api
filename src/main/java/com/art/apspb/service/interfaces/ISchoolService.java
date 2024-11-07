package com.art.apspb.service.interfaces;

import com.art.apspb.dto.SchoolDTO;
import com.art.apspb.model.School;

import java.util.List;

public interface ISchoolService {
    List<School> getAll();
    School getById(Integer id);
    School update(Integer id, SchoolDTO dto);
    void save(SchoolDTO dto);
    boolean delete(Integer id);
}
