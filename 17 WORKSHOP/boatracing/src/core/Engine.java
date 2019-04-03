package core;

import exceptions.*;
import interfaces.CommandHandler;
import interfaces.InputReader;
import interfaces.OutputWriter;

import java.io.IOException;

public class Engine implements Runnable {
    private static final String END_COMMAND = "End";

    private InputReader reader;
    private OutputWriter writer;
    private CommandHandler commandHandler;

    public Engine(InputReader reader, OutputWriter writer, CommandHandler commandHandler) {
        this.reader = reader;
        this.writer = writer;
        this.commandHandler = commandHandler;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (processInput()) break;
            } catch (IOException |
                    DuplicateModelException |
                    ArgumentException |
                    NonExistentModelException |
                    RaceAlreadyExistsException |
                    NoSetRaceException |
                    InsufficientContestantsException e) {

                this.writer.writeLine(e.getMessage());
            }
        }
    }

    private boolean processInput() throws
            IOException,
            ArgumentException,
            NonExistentModelException,
            RaceAlreadyExistsException,
            InsufficientContestantsException,
            NoSetRaceException,
            DuplicateModelException {

        String input = reader.readLine();
        if (END_COMMAND.equals(input)) {
            return true;
        }
        String[] tokens = input.split("\\\\");
        this.writer.writeLine(this.commandHandler.interpretCommand(tokens));
        return false;
    }
}
