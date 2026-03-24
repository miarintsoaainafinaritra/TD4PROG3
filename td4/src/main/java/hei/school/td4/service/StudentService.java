package hei.school.td4.service;

import hei.school.td4.entity.Student;
import hei.school.td4.repository.StudentRepository;
import hei.school.td4.validator.StudentValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentValidator studentValidator;

    public StudentService(StudentRepository studentRepository, StudentValidator studentValidator) {
        this.studentRepository = studentRepository;
        this.studentValidator = studentValidator;
    }
    public List<Student> addStudents(List<Student> newStudents) {

        studentValidator.validateAll(newStudents);

        studentRepository.saveAll(newStudents);

        return studentRepository.findAll();
    }
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
