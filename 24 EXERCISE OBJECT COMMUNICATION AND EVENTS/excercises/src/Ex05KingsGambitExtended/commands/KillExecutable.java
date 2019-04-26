package Ex05KingsGambitExtended.commands;

import Ex05KingsGambitExtended.interfaces.Executable;
import Ex05KingsGambitExtended.interfaces.GuardsGroup;
import Ex05KingsGambitExtended.interfaces.Warrior;

import java.util.Optional;

public class KillExecutable implements Executable {

    private String warriorName;
    private GuardsGroup guardsGroup;

    public KillExecutable(String warriorName, GuardsGroup guardsGroup) {
        this.warriorName = warriorName;
        this.guardsGroup = guardsGroup;
    }

    @Override
    public void execute() {
        guardsGroup.getGuards()
                .stream()
                .filter(x -> x.getName().equals(warriorName))
                .findFirst().ifPresent(x -> x.setHitsCount(x.getHitsCount() + 1));

        Optional<Warrior> warriorOptional = guardsGroup.getGuards()
                .stream()
                .filter(x -> x.getName().equals(warriorName))
                .findFirst();

        warriorOptional.ifPresent(war -> {
            boolean checkFootman = war.getClass().getSimpleName().equals("Footman") && war.getHitsCount() == 2;
            boolean checkRoyalGuard = war.getClass().getSimpleName().equals("RoyalGuards") && war.getHitsCount() == 3;
            if (checkFootman || checkRoyalGuard) {
                this.guardsGroup.remove(this.warriorName);
            }
        });
    }
}
