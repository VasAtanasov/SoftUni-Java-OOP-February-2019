import core.CommandExecutor;
import core.commands.AttackCommand;
import core.commands.TargetCommand;
import interfaces.Attacker;
import interfaces.Command;
import interfaces.Executor;
import interfaces.Target;
import models.Dragon;
import models.Warrior;

public class Main {
    public static void main(String[] args) {

        Attacker attacker = new Warrior("Pesho", 10);
        Target target = new Dragon("Gosho", 10, 5);

        Executor commandExecutor = new CommandExecutor();

        Command setTarget = new TargetCommand(attacker, target);
        Command attackCommand = new AttackCommand(attacker);

        commandExecutor.executeCommand(setTarget);
        commandExecutor.executeCommand(attackCommand);
    }
}
