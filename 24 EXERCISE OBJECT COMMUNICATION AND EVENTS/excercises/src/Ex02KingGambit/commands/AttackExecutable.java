package Ex02KingGambit.commands;

import Ex02KingGambit.interfaces.Executable;
import Ex02KingGambit.interfaces.GuardsGroup;

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
