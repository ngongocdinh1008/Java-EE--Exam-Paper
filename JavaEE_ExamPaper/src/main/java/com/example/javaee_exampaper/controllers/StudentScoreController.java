package com.example.javaee_exampaper.controllers;


import com.example.javaee_exampaper.entitys.StudentScore;
import com.example.javaee_exampaper.repositorys.StudentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-scores")
public class StudentScoreController {
    @Autowired
    private StudentScoreRepository studentScoreRepository;

    @GetMapping
    public List<StudentScore> getAllStudentScores() {
        return studentScoreRepository.findAll();
    }

    @PostMapping
    public StudentScore createStudentScore(@RequestBody StudentScore studentScore) {
        return studentScoreRepository.save(studentScore);
    }

    @GetMapping("/{id}")
    public StudentScore getStudentScoreById(@PathVariable Integer id) {
        return studentScoreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student score not found with id: " + id));
    }

    @PutMapping("/{id}")
    public StudentScore updateStudentScore(@PathVariable Integer id, @RequestBody StudentScore studentScoreDetails) {
        StudentScore studentScore = studentScoreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student score not found with id: " + id));
        studentScore.setStudent(studentScoreDetails.getStudent());
        studentScore.setSubject(studentScoreDetails.getSubject());
        studentScore.setScore1(studentScoreDetails.getScore1());
        studentScore.setScore2(studentScoreDetails.getScore2());
        return studentScoreRepository.save(studentScore);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentScore(@PathVariable Integer id) {
        studentScoreRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
