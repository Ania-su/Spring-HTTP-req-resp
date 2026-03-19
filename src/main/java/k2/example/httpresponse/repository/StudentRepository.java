package k2.example.httpresponse.repository;

import k2.example.httpresponse.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    private final List<Student> students = new ArrayList<>();

    public void addStudents(List<Student> newStudents) {
        students.addAll(newStudents);
    }

    public List<Student> getAllStudents() {
        return students;
    }
}
