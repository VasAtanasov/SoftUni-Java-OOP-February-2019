package cresla.core.commands;

import cresla.interfaces.Command;
import cresla.interfaces.Manager;

import java.util.List;

public class ExitCommand implements Command {
    private Manager manager;

    public ExitCommand(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(List<String> arguments) {
        return this.manager.exitCommand(arguments);
    }
}
