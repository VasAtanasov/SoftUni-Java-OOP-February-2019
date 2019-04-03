import contracts.CommandFactory;
import contracts.Repository;
import contracts.UnitFactory;
import core.Engine;
import core.factories.CommandFactoryImpl;
import core.factories.UnitFactoryImpl;
import data.UnitRepository;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();
        CommandFactory commandFactory = new CommandFactoryImpl();
        Runnable engine = new Engine(repository, unitFactory, commandFactory);
        engine.run();
    }
}
