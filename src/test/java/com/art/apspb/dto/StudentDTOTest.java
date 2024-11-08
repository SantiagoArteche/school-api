package com.art.apspb.dto;

import com.art.apspb.model.School;
import com.art.apspb.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentDTOTest {

    private StudentDTO dto;
    private Student student;


    @BeforeAll
    static void start(){
        System.out.println("Starting tests");
    }


    @Test
    void toStudent() {
        this.dto = new StudentDTO("Test", 35, 3);
        this.student = StudentDTO.toStudent(dto);

        assertEquals(this.dto.name(), this.student.getName());
        assertEquals(this.dto.age(), this.student.getAge());
        assertNotNull(this.student.getSchool());
        assertEquals(this.dto.schoolId(), this.student.getSchool().getId());
        assertInstanceOf(Student.class, this.student);
    }

    @Test
    void shouldThrowNullPointerExceptionWhenDtoIsNull(){
        this.dto = null;

        NullPointerException msg = assertThrows(NullPointerException.class, () -> StudentDTO.toStudent(dto));
        assertEquals(msg.getMessage(), "The student DTO is null");
    }

    @Test
    void name() {
        this.dto = new StudentDTO("Test", null, null);

        assertNotNull(this.dto.name());
        assertInstanceOf(String.class, this.dto.name());
    }



    @Test
    void age() {
        this.dto = new StudentDTO(null, 33, null);

        assertNotNull(this.dto.age());
        assertInstanceOf(Integer.class, this.dto.age());
    }

    @Test
    void schoolId() {
        this.dto = new StudentDTO(null, null, 1);

        assertNotNull(this.dto.schoolId());
        assertInstanceOf(Integer.class, this.dto.schoolId());
    }
}