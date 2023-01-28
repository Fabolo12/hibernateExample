package controller;

import model.Student;
import service.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentController {
    private final StudentService studentService;

    public StudentController(final StudentService studentService) {
        this.studentService = studentService;
    }

    public String create() {
        final Student student = studentService.createAndSave();
        return student.getId();
    }

    public Optional<Student> get(final String studentId) {
        return studentService.getById(studentId);
    }

    public List<String> getStudentsId() {
        return studentService.getAll().stream()
                .map(Student::getId)
                .collect(Collectors.toList());
    }

    public void delete(final String id) {
        studentService.delete(id);
    }
}
