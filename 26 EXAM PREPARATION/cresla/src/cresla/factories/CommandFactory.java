package cresla.factories;

import cresla.interfaces.Command;
import cresla.core.commands.ExitCommand;
import cresla.interfaces.Manager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CommandFactory {
    private static final String COMMANDS_PACKAGE = "cresla.core.commands.";
    private static final String COMMAND_POSTFIX = "Command";

    public static Command createCommand(String commandType, Manager manager) {
        try {
            Class<?> commandClass = Class.forName(COMMANDS_PACKAGE + commandType + COMMAND_POSTFIX);
            Constructor<?> declaredConstructor = commandClass.getDeclaredConstructor(Manager.class);
            return (Command) declaredConstructor.newInstance(manager);
        } catch (ClassNotFoundException |
                InstantiationException |
                InvocationTargetException |
                NoSuchMethodException |
                IllegalAccessException e) {

            return new ExitCommand(manager);
        }
    }
}
