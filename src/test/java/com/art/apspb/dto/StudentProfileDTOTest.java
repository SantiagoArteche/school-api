package com.art.apspb.dto;


import com.art.apspb.model.StudentProfile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentProfileDTOTest {

    StudentProfileDTO dto;
    StudentProfile studentProfile;

    @Test
    void toStudentProfile() {
        this.dto = new StudentProfileDTO("Test", 7);
        this.studentProfile = StudentProfileDTO.toStudentProfile(this.dto);


        assertEquals(this.dto.bio(), this.studentProfile.getBio());
        assertNotNull(this.studentProfile.getStudent());
        assertEquals(this.dto.studentId(), this.studentProfile.getStudent().getId());
        assertInstanceOf(StudentProfile.class, this.studentProfile);
    }

    @Test
    void shouldThrowNullPointerExceptionWhenDtoIsNull(){
        this.dto = null;

        NullPointerException msg = assertThrows(NullPointerException.class, () -> StudentProfileDTO.toStudentProfile(dto));
        assertEquals(msg.getMessage(), "The student profile DTO is null");
    }

    @Test
    void bio() {
        this.dto = new StudentProfileDTO("Test", null);

        assertNotNull(this.dto.bio());
        assertInstanceOf(String.class, this.dto.bio());
    }

    @Test
    void studentId() {
        this.dto = new StudentProfileDTO(null, 7);

        assertNotNull(this.dto.studentId());
        assertInstanceOf(Integer.class, this.dto.studentId());
    }
}