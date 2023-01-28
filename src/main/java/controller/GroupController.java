package controller;

import model.Group;
import model.Student;
import service.GroupService;
import service.StudentService;

import java.util.Optional;

public class GroupController {
    private final GroupService groupService;

    public GroupController(final GroupService groupService) {
        this.groupService = groupService;
    }

    public String create() {
        final Group group = groupService.create();
        return group.getId();
    }

    public Optional<Group> get(final String id) {
        return groupService.get(id);
    }
}
