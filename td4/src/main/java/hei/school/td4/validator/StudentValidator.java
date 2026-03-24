package hei.school.td4.validator;

import hei.school.td4.entity.Student;
import hei.school.td4.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentValidator {

    public void validate(Student student) {
        if (student.getReference() == null || student.getReference().isBlank()) {
            throw new BadRequestException("La référence de l'étudiant ne peut pas être vide");
        }

        if (student.getFirstName() == null || student.getFirstName().isBlank()) {
            throw new BadRequestException("Le prénom de l'étudiant ne peut pas être vide");
        }

        if (student.getLastName() == null || student.getLastName().isBlank()) {
            throw new BadRequestException("Le nom de l'étudiant ne peut pas être vide");
        }
    }

    public void validateAll(List<Student> students) {
        for (Student student : students) {
            validate(student);
        }
    }
}