package hei.school.td4.controller;

import hei.school.td4.entity.Student;
import hei.school.td4.exception.BadRequestException;
import hei.school.td4.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public ResponseEntity<?> createStudents(@RequestBody List<Student> newStudents) {
        try {
            List<Student> allStudents = studentService.addStudents(newStudents);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header("Content-Type", "application/json")
                    .body(allStudents);
        } catch (BadRequestException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .header("Content-Type", "text/plain")
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur serveur : " + e.getMessage());
        }
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader(value = "Accept", required = false) String acceptHeader) {
        try {
            if (acceptHeader == null) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("L'en-tête 'Accept' est requis");
            }

            if ("text/plain".equals(acceptHeader)) {
                List<Student> students = studentService.getAllStudents();
                String names = students.stream()
                        .map(s -> s.getFirstName() + " " + s.getLastName())
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("");
                return ResponseEntity.ok().body(names);
            }
            else if ("application/json".equals(acceptHeader)) {
                return ResponseEntity.ok().body(studentService.getAllStudents());
            }
            else {
                return ResponseEntity
                        .status(HttpStatus.NOT_IMPLEMENTED)
                        .body("Format non supporté. Formats acceptés : text/plain, application/json");
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur serveur : " + e.getMessage());
        }
    }
}
