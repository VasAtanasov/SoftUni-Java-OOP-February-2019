package core.commands;

import annotations.Inject;
import contracts.Repository;

public class Retire extends Command {

    @Inject
    private Repository repository;

    public Retire(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        try {
            this.repository.removeUnit(this.getData()[1]);
        } catch (IllegalArgumentException iae) {
            return iae.getMessage();
        }
        return this.getData()[1] + " retired!";
    }
}
