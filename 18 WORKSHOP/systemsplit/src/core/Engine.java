package core;

import interfaces.CommandHandler;
import interfaces.InputReader;
import interfaces.OutputWriter;
import io.InputReaderImpl;
import io.OutputWriteImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Engine implements Runnable {
    private static final String END_COMMAND = "System Split";

    private InputReader reader;
    private OutputWriter writer;
    private CommandHandler commandHandler;

    public Engine() {
        this.reader = new InputReaderImpl();
        this.writer = new OutputWriteImpl();
        this.commandHandler = new CommandHandlerImpl(this.writer);
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (processInput()) break;
            } catch (IOException e) {
                this.writer.writeLine(e.getMessage());
            } catch (InstantiationException |
                    InvocationTargetException |
                    NoSuchMethodException |
                    IllegalAccessException |
                    ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean processInput() throws
            IOException,
            ClassNotFoundException,
            NoSuchMethodException,
            InstantiationException,
            IllegalAccessException,
            InvocationTargetException {

        String input = reader.readLine();
        if (END_COMMAND.equals(input)) {
            this.commandHandler.interpretCommand(new String[] {input.replaceAll("\\s+", "")});
            return true;
        }
        String[] tokens = Arrays.stream(input.split("[(),\\s]+"))
                .filter(s -> ! s.isEmpty())
                .toArray(String[]::new);
        this.commandHandler.interpretCommand(tokens);
        return false;
    }
}
