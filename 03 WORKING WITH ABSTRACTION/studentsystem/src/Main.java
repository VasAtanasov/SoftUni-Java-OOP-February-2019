import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private enum CommandType {Create, Show}

    private static final String END_COMMAND = "Exit";

    private static BufferedReader reader;
    private static StudentSystem studentSystem;


    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        studentSystem = new StudentSystem();
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (!END_COMMAND.equals(input = reader.readLine())) {
            String[] tokens = input.split(" ");
            parseCommand(tokens);
        }
    }

    private static void parseCommand(String[] args) {
        CommandType command = CommandType.valueOf(args[0]);
        switch (command) {
            case Create:
                createStudent(Arrays.copyOfRange(args, 1, args.length));
                break;
            case Show:
                showStudent(Arrays.copyOfRange(args, 1, args.length));
                break;
        }
    }

    private static void showStudent(String[] tokens) {
        var name = tokens[0];
        Student student = studentSystem.getByName(name);
        if (student == null) {
            return;
        }
        String view = String.format("%s is %s years old.", student.getName(), student.getAge());

        if (student.getGrade() >= 5.00) {
            view += " Excellent student.";
        } else if (student.getGrade() < 5.00 && student.getGrade() >= 3.50) {
            view += " Average student.";
        } else {
            view += " Very nice person.";
        }

        System.out.println(view);
    }

    private static void createStudent(String[] tokens) {
        String name = tokens[0];
        int age = Integer.parseInt(tokens[1]);
        double grade = Double.parseDouble(tokens[2]);
        if (! studentSystem.contains(name)) {
            Student student = new Student(name, age, grade);
            studentSystem.save(name, student);
        }
    }
}
