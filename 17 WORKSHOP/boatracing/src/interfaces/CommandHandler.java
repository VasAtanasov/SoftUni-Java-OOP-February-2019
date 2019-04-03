package interfaces;

import exceptions.*;

public interface CommandHandler {

    String interpretCommand(String[] data) throws DuplicateModelException, ArgumentException, NonExistentModelException, RaceAlreadyExistsException, NoSetRaceException, InsufficientContestantsException;

}
