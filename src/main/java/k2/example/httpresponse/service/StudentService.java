package k2.example.httpresponse.service;

import k2.example.httpresponse.entity.Student;
import k2.example.httpresponse.repository.StudentRepository;
import k2.example.httpresponse.validator.StudentValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> addStudents(List<Student> students) {
        StudentValidator.validate(students); // 🔥 validation ici
        repository.addStudents(students);
        return repository.getAllStudents();
    }

    public List<Student> getAllStudents() {
        return repository.getAllStudents();
    }

    public String getStudentNames() {
        StringBuilder names = new StringBuilder();

        repository.getAllStudents().forEach(s ->
                names.append(s.getFirstName()).append(" ").append(s.getLastName()).append("\n")
        );

        return names.toString();
    }
}