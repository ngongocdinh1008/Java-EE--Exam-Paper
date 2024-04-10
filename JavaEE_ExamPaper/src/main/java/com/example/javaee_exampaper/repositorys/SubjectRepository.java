package com.example.javaee_exampaper.repositorys;

import com.example.javaee_exampaper.entitys.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
