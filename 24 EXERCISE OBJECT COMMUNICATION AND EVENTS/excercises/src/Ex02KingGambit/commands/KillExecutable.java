package Ex02KingGambit.commands;

import Ex02KingGambit.interfaces.Executable;
import Ex02KingGambit.interfaces.GuardsGroup;

public class KillExecutable implements Executable {

    private String warriorName;
    private GuardsGroup guardsGroup;

    public KillExecutable(String waririorName, GuardsGroup guardsGroup) {
        this.warriorName = waririorName;
        this.guardsGroup = guardsGroup;
    }

    @Override
    public void execute() {
        this.guardsGroup.remove(this.warriorName);
    }
}
