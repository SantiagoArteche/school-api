package com.art.apspb.service.interfaces;

import com.art.apspb.dto.StudentDTO;
import com.art.apspb.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getAll();
    Student getById(Integer id);
    List<Student> getByAge(Integer age);
    List<Student> getByName(String name);
    Student update(Integer id, StudentDTO dto);
    void save(StudentDTO dto);
    boolean delete(Integer id);
}
