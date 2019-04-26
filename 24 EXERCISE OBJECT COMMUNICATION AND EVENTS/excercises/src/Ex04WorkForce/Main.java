package Ex04WorkForce;


import Ex04WorkForce.engine.Engine;
import Ex04WorkForce.interfaces.InputReader;
import Ex04WorkForce.interfaces.OutputWriter;
import Ex04WorkForce.interpreters.CommandInterpreterImpl;
import Ex04WorkForce.io.ConsoleInputReader;
import Ex04WorkForce.io.ConsoleOutputWriter;
import Ex04WorkForce.repositories.JobRepository;
import Ex04WorkForce.repositories.Repository;

public class Main {
    public static void main(String[] args) {
        InputReader reader = new ConsoleInputReader();
        OutputWriter writer = new ConsoleOutputWriter();
        Repository repository = new JobRepository();
        CommandInterpreterImpl interpreter = new CommandInterpreterImpl();
        Runnable engine = new Engine(reader, writer, repository, interpreter );
        engine.run();
    }
}
