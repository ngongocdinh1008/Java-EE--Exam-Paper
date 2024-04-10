package com.example.javaee_exampaper.repositorys;

import com.example.javaee_exampaper.entitys.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentScoreRepository extends JpaRepository<StudentScore, Integer> {

}
