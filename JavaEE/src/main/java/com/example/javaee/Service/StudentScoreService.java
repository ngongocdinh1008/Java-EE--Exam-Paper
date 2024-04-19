package com.example.javaee.Service;

import com.example.javaee.Model.StudentScore;
import com.example.javaee.Repository.StudentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentScoreService {
    @Autowired
    private StudentScoreRepository studentScoreRepository;

    public List<StudentScore> getAllScores() {
        return studentScoreRepository.findAll();
    }

    public String getGrade(double averageScore) {
        if (averageScore >= 8.0) {
            return "A";
        } else if (averageScore >= 6.0) {
            return "B";
        } else if (averageScore >= 4.0) {
            return "C";
        } else {
            return "F";
        }
    }

    public StudentScore getStudentScoreById(int id) {
        return studentScoreRepository.findById((long)id).orElse(null);
    }

    public StudentScore saveStudentScore(StudentScore studentScore) {
        return studentScoreRepository.save(studentScore);
    }

    public StudentScore updateStudentScore(StudentScore studentScore) {
        return studentScoreRepository.save(studentScore);
    }

    public void deleteStudentScore(int id) {
        studentScoreRepository.deleteById((long)id);
    }
}
