package Ex05KingsGambitExtended;

import Ex05KingsGambitExtended.engine.Engine;
import Ex05KingsGambitExtended.interfaces.OutputWriter;
import Ex05KingsGambitExtended.io.ConsoleOutputWriter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InstantiationException,
            IOException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {

        //  InputReader reader = new ConsoleInputreader();
        OutputWriter writer = new ConsoleOutputWriter();
        // Repository repository = new HeroRepository();
        Runnable engine = new Engine(writer);
        engine.run();
    }
}

