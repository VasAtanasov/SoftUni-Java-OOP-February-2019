package interfaces;

import exceptions.*;

import java.lang.reflect.InvocationTargetException;

public interface Executable {

	String execute() throws DuplicateModelException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ArgumentException, NonExistentModelException, RaceAlreadyExistsException, NoSetRaceException, InsufficientContestantsException;

}
