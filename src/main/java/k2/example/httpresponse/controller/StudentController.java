package k2.example.httpresponse.controller;

import k2.example.httpresponse.entity.Student;
import k2.example.httpresponse.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/students")
    public String addStudents(@RequestBody List<Student> students) {
        return service.addAndGetStudents(students);
    }
}
