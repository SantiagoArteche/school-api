package com.art.apspb.dto;

import com.art.apspb.model.School;
import com.art.apspb.model.Student;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchoolResponseDTOTest {

    SchoolResponseDTO dto;
    List<SchoolResponseDTO> school;
    List<School> schools = new ArrayList<>();
    List<Student> students = new ArrayList<>();

    @Test
    void toSchoolResponse() {
        this.dto = new SchoolResponseDTO(1000, "Test", new ArrayList<>());
        this.schools.add(new School("Test1"));
        this.schools.add(new School("Test2"));
        this.students.add(new Student(1,"TestStudent1", 35, null, null));
        this.students.add(new Student(2,"TestStudent2", 35,null, null));
        this.schools.getFirst().setStudents(students);
        this.schools.getLast().setStudents(students);
        this.school = SchoolResponseDTO.toSchoolResponse(schools);

        assertEquals(this.school.getFirst().id(), this.schools.getFirst().getId());
        assertEquals(this.school.getLast().id(), this.schools.getLast().getId());
        assertNotNull(this.school.getFirst());
        assertNotNull(this.school.getLast());
        assertInstanceOf(List.class, this.school);
        assertInstanceOf(SchoolResponseDTO.class, this.school.getFirst());
    }

    @Test
    void shouldThrowNullPointerExceptionWhenSchoolListIsNull(){
        this.schools = null;

        NullPointerException msg = assertThrows(NullPointerException.class, () -> SchoolResponseDTO.toSchoolResponse(this.schools));
        assertEquals(msg.getMessage(), "The list of schools is null");
    }

    @Test
    void name() {
        this.dto = new SchoolResponseDTO(null, "Test", null);

        assertNotNull(this.dto.name());
        assertInstanceOf(String.class, this.dto.name());
    }

    @Test
    void id() {
        this.dto = new SchoolResponseDTO(33, null, null);

        assertNotNull(this.dto.id());
        assertInstanceOf(Integer.class, this.dto.id());
    }

    @Test
    void studentsId() {
        this.dto = new SchoolResponseDTO(null, null, new ArrayList<>());
        this.dto.studentsId().add(7);

        assertNotNull(this.dto.studentsId());
        assertInstanceOf(List.class, this.dto.studentsId());
        assertInstanceOf(Integer.class, this.dto.studentsId().getFirst());
    }
}