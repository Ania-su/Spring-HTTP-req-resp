package k2.example.httpresponse.service;

import k2.example.httpresponse.entity.Student;
import k2.example.httpresponse.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public String getStudentNames() {
        List<Student> students = repository.getAllStudents();
        StringBuilder names = new StringBuilder();

        for (Student s : students) {
            names.append(s.getFirstName()).append(" ").append(s.getLastName()).append("\n");
        }
        return names.toString();
    }

    public String addAndGetStudents(List<Student> students) {
        repository.addStudents(students);
        return getStudentNames();
    }
}
