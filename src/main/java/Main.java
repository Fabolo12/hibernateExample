import controller.GroupController;
import controller.StudentController;
import model.Group;
import model.Student;
import repository.GeneralRepository;
import repository.GroupRepository;
import repository.StudentRepository;
import service.GroupService;
import service.StudentService;
import service.SubjectService;

public class Main {
    public static void main(String[] args) {
        final GeneralRepository<Student> studentRepository = new StudentRepository();
        final StudentService studentService = new StudentService(studentRepository);
        final StudentController studentController = new StudentController(studentService);
        //        studentOperations(studentController);

        final SubjectService subjectService = new SubjectService();

        final GeneralRepository<Group> groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository, studentService, subjectService);
        final GroupController groupController = new GroupController(groupService);
        final String groupId = groupController.create();
        groupController.get(groupId).ifPresentOrElse(
                group -> {
                    System.out.println(group);
                    group.getStudents().forEach(student ->
                            System.out.println(student + " " + student.getSubjects().size())
                    );
                },
                () -> System.out.println("Can't find group with id " + groupId)
        );
    }

    private static void studentOperations(final StudentController controller) {
        final String studentId = controller.create();

        controller.get(studentId).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Can't find student with id " + studentId)
        );

        controller.create();
        controller.getStudentsId().forEach(System.out::println);

        controller.delete(studentId);
        controller.get(studentId).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Can't find student with id " + studentId)
        );
    }
}
