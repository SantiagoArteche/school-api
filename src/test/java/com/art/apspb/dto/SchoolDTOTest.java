package com.art.apspb.dto;

import com.art.apspb.model.School;
import com.art.apspb.model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchoolDTOTest {

    private SchoolDTO dto;
    private School school;

    @Test
    void toSchool() {
        this.dto = new SchoolDTO("Test");
        this.school = SchoolDTO.toSchool(dto);

        assertEquals(this.dto.name(), this.school.getName());
        assertInstanceOf(School.class, this.school);
    }

    @Test
    void shouldThrowNullPointerExceptionWhenDtoIsNull(){
        this.dto = null;

        NullPointerException msg = assertThrows(NullPointerException.class, () -> SchoolDTO.toSchool(dto));
        assertEquals(msg.getMessage(), "The school DTO is null");
    }

    @Test
    void name() {
        this.dto = new SchoolDTO("Test");

        assertNotNull(this.dto.name());
        assertInstanceOf(String.class, this.dto.name());
    }

}