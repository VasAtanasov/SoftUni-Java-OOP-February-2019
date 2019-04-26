package Ex02KingGambit;

import Ex02KingGambit.interfaces.GuardsGroup;
import Ex02KingGambit.interfaces.Warrior;

import java.util.*;

public class GroupOfGuards implements GuardsGroup {
    private List<Warrior> guards;

    public GroupOfGuards() {
        this.guards = new ArrayList<>();
    }


    @Override
    public List<Warrior> getGuards() {
        return  Collections.unmodifiableList(this.guards);
    }

    @Override
    public void addMember(Warrior guard) {
        this.guards.add(guard);
    }

    @Override
    public void remove(String name) {
        guards.removeIf(warrior -> warrior.getName().equalsIgnoreCase(name));
    }

    @Override
    public void groupAttack() {
        for (Warrior guard : guards) {
//            if(!guards.getClass().getSimpleName().equalsIgnoreCase("King")){
//                guard.handle();
//            }
            guard.handle();
        }
    }
}
