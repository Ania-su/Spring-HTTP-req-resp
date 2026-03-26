package k2.example.httpresponse.controller;

import k2.example.httpresponse.entity.Student;
import k2.example.httpresponse.exception.BadRequestException;
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
    public ResponseEntity<?> addStudents(@RequestBody List<Student> students) {
        try {
            List<Student> result = service.addStudents(students);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);

        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader(value = "Accept", required = false) String accept) {
        try {
            if (accept == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Header Accept manquant");
            }

            if (accept.equals("text/plain")) {
                return ResponseEntity.ok(service.getStudentNames());
            }

            if (accept.equals("application/json")) {
                return ResponseEntity.ok(service.getAllStudents());
            }

            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Format non supporté");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}