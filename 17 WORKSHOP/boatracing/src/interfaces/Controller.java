package interfaces;

import exceptions.*;

public interface Controller {
    String createRowBoat(String model, Integer weight, Integer oars) throws DuplicateModelException, ArgumentException;

    String createBoatEngine(String model, Integer horsepower, Integer displacement, String type) throws DuplicateModelException, ArgumentException;

    String createSailBoat(String model, Integer weight, Integer sailEfficiency) throws ArgumentException, DuplicateModelException;

    String createPowerBoat(String model, Integer weight, String engineOneModel, String engineTwoModel) throws ArgumentException, NonExistentModelException, DuplicateModelException;

    String createYacht(String model, Integer weight, String engine, Integer cargoWeight) throws ArgumentException, NonExistentModelException, DuplicateModelException;

    String openRace(Integer distance, Integer windSpeed, Integer currentSpeed, boolean allowsMotorboats) throws RaceAlreadyExistsException, ArgumentException;

    String signUpBoat(String model) throws NoSetRaceException, NonExistentModelException, ArgumentException, DuplicateModelException;

    String startRace() throws NoSetRaceException, InsufficientContestantsException;

    String getStatistics() throws NoSetRaceException;

}
