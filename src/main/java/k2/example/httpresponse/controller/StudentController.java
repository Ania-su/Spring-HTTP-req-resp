package k2.example.httpresponse.controller;

import k2.example.httpresponse.entity.Student;
import k2.example.httpresponse.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/students")
    public ResponseEntity<List<Student>> addStudents(@RequestBody List<Student> students) {
        try {
            List<Student> allStudents = service.addAndGetStudents(students);
            return ResponseEntity.status(HttpStatus.CREATED).body(allStudents);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/students")
    public String getStudents(@RequestHeader(value = "Accept", defaultValue = "text/plain") String accept) {
        if (!accept.contains("text/plain")) {
            return "Unsupported Format";
        }
        return service.getStudentNames();
    }
}
