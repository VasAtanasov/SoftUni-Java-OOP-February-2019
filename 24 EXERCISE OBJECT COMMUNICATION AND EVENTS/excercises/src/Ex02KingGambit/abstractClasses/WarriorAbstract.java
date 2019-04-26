package Ex02KingGambit.abstractClasses;


import Ex02KingGambit.interfaces.Warrior;

public abstract class WarriorAbstract implements Warrior {
    private String name;

    public WarriorAbstract(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void handle() {
    }
}
