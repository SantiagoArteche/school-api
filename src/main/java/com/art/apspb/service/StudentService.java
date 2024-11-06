package com.art.apspb.service;

import com.art.apspb.model.Student;
import com.art.apspb.repository.StudentRepository;
import com.art.apspb.service.interfaces.IStudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAll() {
        return this.studentRepository.findAll();
    }

    @Override
    public Student getById(Integer id) {
        return this.studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> getByAge(Integer age) {
        return this.studentRepository.getByAge(age).orElse(null);
    }

    @Override
    public List<Student> getByName(String name) {
        return this.studentRepository.getByName(name.toLowerCase()).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        this.studentRepository.deleteById(id);
    }

    @Override
    public void update(Student student) {
        this.studentRepository.save(student);
    }
}
