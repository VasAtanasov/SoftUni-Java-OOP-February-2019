package Ex05KingsGambitExtended.warriors;

import Ex05KingsGambitExtended.abstractClasses.WarriorAbstract;

public class King extends WarriorAbstract {

    public King(String name) {
        super(name);
    }

    @Override
    public void handle() {
        System.out.printf("King %s is under attack!%n", super.getName());
    }
}
