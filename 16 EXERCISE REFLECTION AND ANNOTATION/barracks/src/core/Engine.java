package core;

import annotations.Inject;
import contracts.CommandFactory;
import contracts.Repository;
import contracts.UnitFactory;
import core.commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {

    private Repository repository;
    private UnitFactory unitFactory;
    private CommandFactory commandFactory;

    public Engine(Repository repository, UnitFactory unitFactory, CommandFactory commandFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
        this.commandFactory = commandFactory;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String input = reader.readLine();
                String[] data = input.split("\\s+");
                String commandName = data[0];
                String result = interpretCommand(data, commandName);
                if (result.equals("fight")) {
                    break;
                }
                System.out.println(result);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (IOException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private String interpretCommand(String[] data, String commandName) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Command command = this.commandFactory.createCommand(this.capitalizeFirstLetter(commandName), data);
        this.inject(command);
        return command.execute();
    }

    private String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }

    private void inject(Command command) throws IllegalAccessException {
        Field[] commandFields = command.getClass().getDeclaredFields();
        Field[] thisFields = this.getClass().getDeclaredFields();

        for (Field commandField : commandFields) {
            commandField.setAccessible(true);
            if (commandField.isAnnotationPresent(Inject.class)) {
                Class<?> commandFieldType = commandField.getType();
                for (Field thisField : thisFields) {
                    Class<?> thisFieldType = thisField.getType();
                    if (commandFieldType.equals(thisFieldType)) {
                        commandField.setAccessible(true);
                        commandField.set(command, thisField.get(this));
                    }
                }
            }
        }
    }
}
