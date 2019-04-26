package Ex05KingsGambitExtended;

import Ex05KingsGambitExtended.interfaces.GuardsGroup;
import Ex05KingsGambitExtended.interfaces.Warrior;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupOfGuards implements GuardsGroup {
    private List<Warrior> guards;

    public GroupOfGuards() {
        this.guards = new ArrayList<>();
    }

    @Override
    public List<Warrior> getGuards() {
        return Collections.unmodifiableList(this.guards);
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
            guard.handle();
        }
    }
}
