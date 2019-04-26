package Ex02KingGambit.engine;

import Ex02KingGambit.GroupOfGuards;
import Ex02KingGambit.commands.AttackExecutable;
import Ex02KingGambit.commands.KillExecutable;
import Ex02KingGambit.factories.WarriorFactoryImpl;
import Ex02KingGambit.interfaces.*;
import Ex02KingGambit.io.ConsoleInputreader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {
    private static final GuardsGroup guardsGroup = new GroupOfGuards();
    private static final WarriorFactory warriorFactory = new WarriorFactoryImpl();
    private static final InputReader reader = new ConsoleInputreader();

    private OutputWriter writer;

    public Engine(OutputWriter writer) {
        this.writer = writer;
    }

    @Override
    public void run() {
        try {
            readInput();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | IOException | InvocationTargetException e) {
            e.printStackTrace();
        }

        while (true) {
            String[] tokens = reader.readLine().split("\\s+");

            if ("End".equalsIgnoreCase(tokens[0])) {
                break;
            }

            String command = tokens[0];

            switch (command) {
                case "Attack":
                    Executable attackExecutable = new AttackExecutable(guardsGroup);
                    attackExecutable.execute();
                    break;
                case "Kill":
                    String name = tokens[1];
                    Executable killExecutable = new KillExecutable(name, guardsGroup);
                    killExecutable.execute();
                    break;
            }
        }
    }

    private static void readInput() throws ClassNotFoundException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException, IOException {

        String[] kingsName = reader.readLine().split("\\s+");
        addWarrior(kingsName, "King");

        String[] guardsArr = reader.readLine().split("\\s+");
        addWarrior(guardsArr, "RoyalGuards");

        String[] footmanArr = reader.readLine().split("\\s+");
        addWarrior(footmanArr, "Footman");
    }

    private static void addWarrior(String[] warriorArr, String warriorType) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (int i = 0; i < warriorArr.length; i++) {
            Warrior warrior = warriorFactory.createWarrior(warriorType, warriorArr[i]);
            guardsGroup.addMember(warrior);
        }
    }
}
