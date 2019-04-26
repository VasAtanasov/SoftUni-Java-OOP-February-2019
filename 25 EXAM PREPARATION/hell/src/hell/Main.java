package hell;


import hell.core.CommandHandlerImpl;
import hell.core.Engine;
import hell.core.ManagerImpl;
import hell.interfaces.CommandHandler;
import hell.interfaces.InputReader;
import hell.interfaces.Manager;
import hell.interfaces.OutputWriter;
import hell.io.InputReaderImpl;
import hell.io.OutputWriterImpl;

public class Main {
    public static void main(String[] args) {
        Manager manager = new ManagerImpl();
        CommandHandler commandHandler = new CommandHandlerImpl(manager);
        InputReader reader = new InputReaderImpl();
        OutputWriter writer = new OutputWriterImpl();
        Runnable engine = new Engine(reader, writer, commandHandler);
        engine.run();
    }
}