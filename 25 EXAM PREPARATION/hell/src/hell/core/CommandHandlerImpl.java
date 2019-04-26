package hell.core;


import hell.interfaces.CommandHandler;
import hell.interfaces.Manager;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("Duplicates")
public class CommandHandlerImpl implements CommandHandler {

    private enum Commands {
        Hero,
        Item,
        Recipe,
        Inspect,
        Quit,
    }

    private final Manager manager;

    public CommandHandlerImpl(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String handleCommand(String[] data) throws
            IllegalAccessException,
            NoSuchFieldException {

        Commands command = Commands.valueOf(data[0]);
        List<String> args = List.of(Arrays.copyOfRange(data, 1, data.length));

        switch (command) {
            case Hero:
                return this.manager.addHero(args);
            case Item:
                return this.manager.addItem(args);
            case Recipe:
                return this.manager.addRecipe(args);
            case Inspect:
                return this.manager.inspect(args);
            case Quit:
                return this.manager.quit();
        }

        throw new IllegalArgumentException();
    }
}
