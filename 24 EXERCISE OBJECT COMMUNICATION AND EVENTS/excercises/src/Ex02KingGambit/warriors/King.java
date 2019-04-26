package Ex02KingGambit.warriors;

import Ex02KingGambit.abstractClasses.WarriorAbstract;

public class King extends WarriorAbstract {

    public King(String name) {
        super(name);
    }

    @Override
    public void handle() {
        System.out.printf("King %s is under attack!%n", super.getName());

    }
}
