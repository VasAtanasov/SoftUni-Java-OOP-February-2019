package Ex04WorkForce.engine;

import Ex04WorkForce.interfaces.*;
import Ex04WorkForce.interpreters.CommandInterpreterImpl;
import Ex04WorkForce.repositories.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Engine implements Runnable {
    private static final String TERMINATE_COMMAND = "End";
    private static final String COMMAND_CLASS_PATH = "hell.core.commands.";
    private static final String COMMAND_CLASS_NAME_SUFFIX = "Command";

    private List<String> params;
    private InputReader reader;
    private OutputWriter writer;
    private Repository repository;
    private CommandInterpreterImpl interpreter;

    public Engine(InputReader reader, OutputWriter writer, Repository repository, CommandInterpreterImpl interpreter) {
        this.reader = reader;
        this.writer = writer;
        this.repository = repository;
        this.interpreter = interpreter;
    }

    @Override
    public void run() {
        while (true) {
            List<String> tokens = new ArrayList<>(Arrays.asList(this.reader.readLine().split("\\s+")));

            if (TERMINATE_COMMAND.equals(tokens.get(0))) {
                break;
            }

            try {
                this.dispatchCommand(tokens);
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    private void dispatchCommand(List<String> commandParams) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String command = commandParams.remove(0);

        switch (command) {
            case "StandartEmployee":
                String employeeType = command;
                String name = commandParams.get(0);
                interpreter.createEmployee(name, employeeType);
                break;
            case "PartTimeEmployee":
                employeeType = command;
                name = commandParams.get(0);
                interpreter.createEmployee(name, employeeType);
                break;
            case "Job":
                String jobName = commandParams.get(0);
                int hoursOfWorkRequired = Integer.parseInt(commandParams.get(1));
                String employeeName = commandParams.get(2);
                interpreter.createJob(jobName, hoursOfWorkRequired, employeeName);
                break;
            case "Pass":
                interpreter.jobsUpdate();
                break;
            case "Status":
                interpreter.printJobStatus();
        }
    }
}
