package com.example.mybaseballdb.controller;

import com.example.mybaseballdb.model.Student;
import com.example.mybaseballdb.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam("studentNumber") String studentNumber, Model model) {
        Optional<Student> student = studentService.findByStudentNumber(studentNumber);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
        } else {
            model.addAttribute("error", "No student found with the given number.");
        }
        return "result";
    }
}
