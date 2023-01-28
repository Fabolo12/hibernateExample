package service;

import model.Subject;

import java.util.Random;

public class SubjectService {
    private static final Random RANDOM = new Random();

    public Subject create() {
        final Subject subject = new Subject();
        subject.setName("Student-" + RANDOM.nextInt(1000));
        return subject;
    }
}
