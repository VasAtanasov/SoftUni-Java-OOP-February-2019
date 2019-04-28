package cresla.core.commands;

import cresla.interfaces.Command;
import cresla.interfaces.Manager;

import java.util.List;

public class ModuleCommand implements Command {
    private Manager manager;

    public ModuleCommand(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(List<String> arguments) {
        return this.manager.moduleCommand(arguments);
    }
}
