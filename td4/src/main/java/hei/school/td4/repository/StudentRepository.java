package hei.school.td4.repository;

import hei.school.td4.entity.Student;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    private final List<Student> students = new ArrayList<>();

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    public void saveAll(List<Student> newStudents) {
        students.addAll(newStudents);
    }
}
