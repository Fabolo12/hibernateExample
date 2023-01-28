package service;

import model.Group;
import model.Student;
import model.Subject;
import repository.GeneralRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class GroupService {
    private static final Random RANDOM = new Random();

    private final GeneralRepository<Group> repository;

    private final StudentService studentService;

    private final SubjectService subjectService;

    public GroupService(final GeneralRepository<Group> repository,
                        final StudentService studentService,
                        final SubjectService subjectService) {
        this.repository = repository;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    public Group create() {
        final Group group = new Group();
        group.setName("Group-" + RANDOM.nextInt(1000));

        final Set<Student> students = createAndLinkStudents(group);
        final Set<Subject> subjects = createSubjects();
        linkStudentAndSubject(students, subjects);

        repository.save(group);
        return group;
    }

    private void linkStudentAndSubject(final Set<Student> students, final Set<Subject> subjects) {
        students.forEach(student -> student.setSubjects(subjects));
        subjects.forEach(subject -> subject.setStudents(students));
    }

    private Set<Subject> createSubjects() {
        final Set<Subject> subjects = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            final Subject subject = subjectService.create();
            subjects.add(subject);
        }
        return subjects;
    }

    private Set<Student> createAndLinkStudents(final Group group) {
        final Set<Student> students = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            final Student student = studentService.create();
            student.setGroup(group);
            students.add(student);
        }
        group.setStudents(students);
        return students;
    }

    public Optional<Group> get(final String id) {
        return repository.getById(id);
    }
}
