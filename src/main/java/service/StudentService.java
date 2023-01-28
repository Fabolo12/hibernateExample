package service;

import model.Student;
import model.Subject;
import repository.GeneralRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class StudentService {
    private final GeneralRepository<Student> repository;
    
    private static final Random RANDOM = new Random();

    public StudentService(GeneralRepository<Student> repository) {
        this.repository = repository;
    }
    
    public Student createAndSave() {
        final Student student = create();
        repository.save(student);
        return student;
    }

    public Student create() {
        final Student student = new Student();
        student.setName("Student-" + RANDOM.nextInt(1000));
        return student;
    }

    public Optional<Student> getById(final String studentId) {
        return repository.getById(studentId);
    }

    public List<Student> getAll() {
        return repository.getAll();
    }

    public void delete(final String id) {
        repository.delete(id);
    }
}
