package com.art.apspb.repository;
import com.art.apspb.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "SELECT * FROM students WHERE LOWER(name) = :name", nativeQuery = true)
    public Optional<List<Student>> getByName(String name);


    @Query(value = "SELECT * FROM students WHERE age >= :age", nativeQuery = true)
    public Optional<List<Student>> getByAge(Integer age);
}
