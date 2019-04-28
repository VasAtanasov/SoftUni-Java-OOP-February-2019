package cresla.core;

import cresla.interfaces.Command;
import cresla.factories.CommandFactory;
import cresla.interfaces.InputReader;
import cresla.interfaces.Manager;
import cresla.interfaces.OutputWriter;

import java.util.Arrays;
import java.util.List;

public class Engine implements Runnable {
    private static final String END_COMMAND = "Exit";

    private InputReader reader;
    private OutputWriter writer;
    private Manager manager;

    public Engine(InputReader reader, OutputWriter writer, Manager manager) {
        this.reader = reader;
        this.writer = writer;
        this.manager = manager;
    }

    @Override
    public void run() {
        String input;
        do {
            input = reader.readLine();
            List<String> tokens = Arrays.asList(input.split("\\s+"));

            Command command = CommandFactory.createCommand(tokens.get(0), this.manager);
            List<String> arguments = tokens.subList(1, tokens.size());
            this.writer.writeLine(command.execute(arguments));

        } while (! END_COMMAND.equals(input));
    }
}
