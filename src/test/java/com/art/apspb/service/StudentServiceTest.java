package com.art.apspb.service;

import com.art.apspb.dto.StudentDTO;
import com.art.apspb.model.Student;
import com.art.apspb.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
class StudentServiceTest {

    @InjectMocks
    private StudentService service;

    @Mock
    private StudentRepository repository;


    private List<Student> students = new ArrayList<>();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll() {
        students.add(new Student("Test", 25));

        when(this.repository.findAll()).thenReturn(students);

        List<Student> findAllStudents = this.service.getAll();

        assertEquals(findAllStudents, students);
        assertEquals(findAllStudents.size(), students.size());
        assertEquals(findAllStudents.getFirst(), students.getFirst());
        assertInstanceOf(List.class, findAllStudents);
        assertNotNull(findAllStudents.getFirst());
        assertInstanceOf(Student.class, findAllStudents.getFirst());
        verify(this.repository, times(1)).findAll();
    }

    @Test
    void getById() {
        Student student1 = new Student(33, "Test", 25, null,null);
        int studentId = 33;
        students.add(student1);

        when(this.repository.findById(studentId)).thenReturn(Optional.of(student1));

        Student student = this.service.getById(studentId);

        assertEquals(student1, student);
        assertInstanceOf(Student.class, student);

        verify(this.repository, times(1)).findById(studentId);
    }

    @Test
    void getByAge() {
        Student student1 = new Student(33, "Test", 25, null,null);
        Student student2 = new Student(33, "Test", 25, null,null);
        Student student3 = new Student(33, "Test", 11, null,null);
        students.add(student1);
        students.add(student2);
        students.add(student3);
        List<Student> listByAge = new ArrayList<>();
        Integer studentsMinAge = 25;

        this.students.forEach(student -> {
            if(student.getAge() >= studentsMinAge){
                listByAge.add(student);
            }
        });

        when(this.repository.getByAge(studentsMinAge)).thenReturn(listByAge);

       List<Student> studentsByAge = this.service.getByAge(studentsMinAge);

        assertEquals(this.students.size(), studentsByAge.size() + 1);
        assertEquals(listByAge.size(), studentsByAge.size());

        verify(this.repository, times(1)).getByAge(studentsMinAge);
    }

    @Test
    void getByName() {
        Student student1 = new Student(33, "Testing", 25, null,null);
        Student student2 = new Student(33, "TestB", 25, null,null);
        students.add(student1);
        students.add(student2);

        List<Student> listByName = new ArrayList<>();
        String studentsName = "Testing";

        this.students.forEach(student -> {
            if(student.getName().toLowerCase().equals(studentsName.toLowerCase())){
                listByName.add(student);
            }
        });

        when(this.repository.getByName(studentsName.toLowerCase())).thenReturn(listByName);

        List<Student> studentsByName = this.service.getByName(studentsName.toLowerCase());

        assertEquals(students.size(), studentsByName.size() + 1);
        assertEquals(studentsByName.size(), listByName.size());

        verify(this.repository, times(1)).getByName(studentsName.toLowerCase());
    }

    @Test
    void save() {
        StudentDTO dto = new StudentDTO("Test", 35, 3);

        Student student = StudentDTO.toStudent(dto);

        this.service.save(dto);

        verify(this.repository, times(1)).save(student);
    }

    @Test
    void update() {
        Student studentToUpdate = new Student(3);
        StudentDTO dto = new StudentDTO("Test", 35, 3);
        Student studentDto = StudentDTO.toStudent(dto);
        studentDto.setId(studentToUpdate.getId());

        when(this.repository.findById(studentToUpdate.getId())).thenReturn(Optional.of(studentToUpdate));
        when(this.repository.save(studentDto)).thenReturn(studentToUpdate);

        Student student2 = this.service.update(studentToUpdate.getId(), dto);

        assertEquals(student2.getAge(), dto.age());
        assertEquals(student2.getName(), dto.name());
        assertNotNull(student2.getSchool());
        assertEquals(student2.getSchool().getId(), dto.schoolId());

        verify(this.repository, times(1)).findById(studentToUpdate.getId());
        verify(this.repository,times(1)).save(studentDto);
    }

    @Test
    void delete() {
        Student student1 = new Student(33, "Test", 25, null,null);
        students.add(student1);
        int studentId = 33;


        when(this.repository.findById(studentId)).thenReturn(Optional.of(student1));

        boolean isDeleted = this.service.delete(studentId);

        assertTrue(isDeleted);

        verify(this.repository, times(1)).findById(studentId);
        verify(this.repository, times(1)).deleteById(studentId);
    }
}