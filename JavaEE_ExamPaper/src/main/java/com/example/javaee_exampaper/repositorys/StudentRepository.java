package com.example.javaee_exampaper.repositorys;

import com.example.javaee_exampaper.entitys.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
