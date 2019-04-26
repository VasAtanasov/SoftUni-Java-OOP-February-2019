package hell.core;

import hell.interfaces.CommandHandler;
import hell.interfaces.InputReader;
import hell.interfaces.OutputWriter;

public class Engine implements Runnable {
    private static final String END_COMMAND = "Quit";

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
            } catch (IllegalAccessException | NoSuchFieldException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean processInput() throws
            IllegalAccessException,
            NoSuchFieldException {

        String input = reader.readLine();
        String[] tokens = input.split("\\s+");

        this.writer.writeLine(this.commandHandler.handleCommand(tokens));

        return END_COMMAND.equals(input);
    }
}
