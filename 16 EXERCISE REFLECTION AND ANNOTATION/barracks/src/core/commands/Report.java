package core.commands;

import annotations.Inject;
import contracts.Repository;

public class Report extends Command {

    @Inject
    private Repository repository;

    public Report(String[] data) {
        super(data);
    }

    @Override
    public String execute()  {
        return this.repository.getStatistics();
    }
}
