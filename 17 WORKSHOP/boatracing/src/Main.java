import core.CommandHandlerImpl;
import core.Database;
import core.Engine;
import core.MainController;
import interfaces.CommandHandler;
import interfaces.Controller;
import interfaces.InputReader;
import interfaces.OutputWriter;
import io.InputReaderImpl;
import io.OutputWriteImpl;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        Controller controller = new MainController(database);
        CommandHandler commandHandler = new CommandHandlerImpl(controller);
        InputReader reader = new InputReaderImpl();
        OutputWriter writer = new OutputWriteImpl();
        Runnable engine = new Engine(reader, writer, commandHandler);
        engine.run();
    }
}
