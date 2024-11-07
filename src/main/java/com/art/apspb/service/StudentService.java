package com.art.apspb.service;

import com.art.apspb.dto.StudentDTO;
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
    public void save(StudentDTO dto){
        Student student = StudentDTO.toStudent(dto);
        this.studentRepository.save(student);
    }

    @Override
    public Student update(Integer id, StudentDTO dto) {
        Student findStudent = this.studentRepository.findById(id).orElse(null);

        if(findStudent != null){
            Student student = StudentDTO.toStudent(dto);
            student.setId(findStudent.getId());

            if(student.getSchool() == null){
                student.setSchool(findStudent.getSchool());
            }

            this.studentRepository.save(student);
        }

        return findStudent;
    }

    @Override
    public boolean delete(Integer id) {
        Student findStudent= this.studentRepository.findById(id).orElse(null);
        if(findStudent != null){
            this.studentRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
