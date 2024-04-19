package com.example.javaee.Controller;

import com.example.javaee.Model.StudentScore;
import com.example.javaee.Service.StudentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-scores")
public class StudentScoreController {
    @Autowired
    private StudentScoreService studentScoreService;

    @GetMapping("/calculate-grade")
    public String calculateGrade(@RequestParam("score1") double score1, @RequestParam("score2") double score2) {
        return studentScoreService.getGrade(0.3 * score1 + 0.7 * score2);
    }

    @GetMapping("/")
    public List<StudentScore> getAllStudentScores() {
        return studentScoreService.getAllScores();
    }

    @GetMapping("/{id}")
    public StudentScore getStudentScoreById(@PathVariable int id) {
        return studentScoreService.getStudentScoreById(id);
    }

    @PostMapping("/")
    public StudentScore addStudentScore(@RequestBody StudentScore studentScore) {
        return studentScoreService.saveStudentScore(studentScore);
    }

    @PutMapping("/{id}")
    public StudentScore updateStudentScore(@PathVariable int id, @RequestBody StudentScore studentScore) {
        studentScore.setId(id);
        return studentScoreService.saveStudentScore(studentScore);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentScore(@PathVariable int id) {
        studentScoreService.deleteStudentScore(id);
    }
}
