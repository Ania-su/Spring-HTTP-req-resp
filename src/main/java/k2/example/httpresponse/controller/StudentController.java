package k2.example.httpresponse.controller;

import k2.example.httpresponse.entity.Student;
import k2.example.httpresponse.repository.StudentRepository;
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
    public ResponseEntity<List<Student>> getStudents(@RequestHeader(value = "Accept", defaultValue = "text/plain") String accept) {
        StudentRepository repository = new StudentRepository();
        try {
            if (accept == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            if (accept.equals("text/plain")) {
                StringBuilder names = new StringBuilder();

                repository.getAllStudents().forEach(s ->
                        names.append(s.getFirstName()).append(" ").append(s.getLastName()).append("\n")
                );
                return ResponseEntity.ok().build();

            }
            else if (accept.equals("application/json")) {
                List<Student> students = repository.getAllStudents();
                return ResponseEntity.ok(students);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
