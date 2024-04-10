package com.example.javaee_exampaper.controllers;

import com.example.javaee_exampaper.entitys.Subject;
import com.example.javaee_exampaper.repositorys.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @PostMapping
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectRepository.save(subject);
    }

    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable Integer id) {
        return subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + id));
    }

    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable Integer id, @RequestBody Subject subjectDetails) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + id));
        subject.setSubjectCode(subjectDetails.getSubjectCode());
        subject.setSubjectName(subjectDetails.getSubjectName());
        subject.setCredit(subjectDetails.getCredit());
        return subjectRepository.save(subject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Integer id) {
        subjectRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
