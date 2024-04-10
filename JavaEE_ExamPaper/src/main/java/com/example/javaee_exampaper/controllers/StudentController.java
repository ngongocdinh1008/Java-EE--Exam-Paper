package com.example.javaee_exampaper.controllers;

import com.example.javaee_exampaper.models.Grade;
import com.example.javaee_exampaper.models.GradeCalculator;
import com.example.javaee_exampaper.entitys.Student;
import com.example.javaee_exampaper.entitys.StudentScore;
import com.example.javaee_exampaper.repositorys.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        List<StudentScore> studentScores = student.getStudentScores();
        if (studentScores.size() == 2) {
            BigDecimal score1 = studentScores.get(0).getScore1();
            BigDecimal score2 = studentScores.get(1).getScore2();
            try {
                Grade grade = GradeCalculator.calculateGrade(score1, score2);
                student.setGrade(grade != null ? grade.toString() : null);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return student;
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student studentDetails) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        student.setStudentCode(studentDetails.getStudentCode());
        student.setFullName(studentDetails.getFullName());
        student.setAddress(studentDetails.getAddress());
        return studentRepository.save(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/{studentCode}")
    public List<Student> findStudentsByStudentCode(@PathVariable String studentCode) {
        return studentRepository.findByStudentCodeContainingIgnoreCase(studentCode);
    }

    // Hiển thị thông tin sinh viên bao gồm điểm số và xếp loại (đã cập nhật)
    @GetMapping("/{id}/scores")
    public Student getStudentScoresById(@PathVariable Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        List<StudentScore> studentScores = student.getStudentScores();
        student.setStudentScores(studentScores); // Set lại danh sách điểm (có thể bỏ nếu không cần thiết)

        // Thêm thông tin xếp loại vào sinh viên
        if (studentScores.size() == 2) {
            BigDecimal score1 = studentScores.get(0).getScore1();
            BigDecimal score2 = studentScores.get(1).getScore2();
            try {
                Grade grade = GradeCalculator.calculateGrade(score1, score2);
                student.setGrade(grade != null ? grade.toString() : null);
            } catch (IllegalArgumentException e) {
                // Xử lý lỗi
                System.out.println(e.getMessage());
            }
        }

        return student;
    }
}
