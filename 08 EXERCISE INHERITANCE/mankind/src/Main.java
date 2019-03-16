import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] studentInput = reader.readLine().split("\\s+");
            String studentFirstName = studentInput[0];
            String studentLasName = studentInput[1];
            String facultyNumber = studentInput[2];
            Student student = new Student(studentFirstName, studentLasName, facultyNumber);
            System.out.println(student);

            String[] workerInput = reader.readLine().split("\\s+");
            String workerFirstName = workerInput[0];
            String workerLastName = workerInput[1];
            double salary = Double.parseDouble(workerInput[2]);
            double workHours = Double.parseDouble(workerInput[3]);
            Worker worker = new Worker(workerFirstName, workerLastName, salary, workHours);

            System.out.println(worker);


        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
    }
}

