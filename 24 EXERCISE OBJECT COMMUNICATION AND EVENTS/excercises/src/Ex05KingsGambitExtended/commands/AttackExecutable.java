package Ex05KingsGambitExtended.commands;

import Ex05KingsGambitExtended.interfaces.Executable;
import Ex05KingsGambitExtended.interfaces.GuardsGroup;

public class AttackExecutable implements Executable {

    private GuardsGroup guardsGroup;

    public AttackExecutable(GuardsGroup guardsGroup) {
        this.guardsGroup = guardsGroup;
    }

    @Override
    public void execute() {
        guardsGroup.groupAttack();
    }
}
