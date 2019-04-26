package Ex02KingGambit.warriors;

import Ex02KingGambit.abstractClasses.WarriorAbstract;

public class Footman extends WarriorAbstract {

    public Footman(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void handle() {
        System.out.printf("Footman %s is panicking!%n", super.getName());
    }
}
