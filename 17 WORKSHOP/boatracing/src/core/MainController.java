package core;

import exceptions.*;
import interfaces.Controller;
import models.boats.*;
import models.engines.BoatEngine;
import models.engines.JetBoatEngine;
import models.engines.SterndriveBoatEngine;
import models.races.Race;

import java.util.*;
import java.util.stream.Collectors;

public class MainController implements Controller {
    private static final String ILLEGAL_MODEL_NAME = "Model's name must be at least %d symbols long.";
    private static final String ILLEGAL_PARAMETER = "%s must be a positive integer.";
    private static final String ILLEGAL_SAILING_EFFICIENCY = "Sail Effectiveness must be between [1...100].";

    private static final String BOAT_CREATED_SUCCESSFULLY = "%s with model %s registered successfully.";
    private static final String BOAT_ENGINE_CREATED_SUCCESSFULLY = "Engine model %s with %d HP and displacement %d cm3 created successfully.";
    private static final String RACE_CREATED_SUCCESSFULLY = "A new race with distance %d meters, wind speed %d m/s and ocean current speed %d m/s has been set.";
    private static final String SING_UP_SUCCESSFULLY = "Boat with model %s has signed up for the current Race.";

    private static final String WEIGHT = "Weight";
    private static final String OARS = "Oars";
    private static final String HORSEPOWER = "Horsepower";
    private static final String DISPLACEMENT = "Displacement";
    private static final String CARGO_WEIGHT = "Cargo Weight";
    private static final String DISTANCE = "Distance";

    private static final String ROW_BOAT = "Row boat";
    private static final String SAIL_BOAT = "Sail boat";
    private static final String POWER_BOAT = "Power boat";
    private static final String YACHT = "Yacht";

    private static final String ROW_BOAT_CLASS = "RowBoat";
    private static final String POWER_BOAT_CLASS = "PowerBoat";
    private static final String YACHT_BOAT_CLASS = "Yacht";
    private static final String SAIL_BOAT_CLASS = "SailBoat";
    private static final String JET = "Jet";
    private static final String STERNDRIVE = "Sterndrive";

    private static final int BOAT_MODEL_LENGTH = 5;
    private static final int ENGINE_MODEL_LENGTH = 3;
    private static final int INVALID_VALUE = 0;

    private Database database;
    private Race race;

    public MainController(Database database) {
        this.database = database;
    }

    @Override
    public String createBoatEngine(String model, Integer horsepower, Integer displacement, String type) throws DuplicateModelException, ArgumentException {
        this.modelCheck(model, ENGINE_MODEL_LENGTH);
        this.parameterCheck(horsepower, HORSEPOWER);
        this.parameterCheck(displacement, DISPLACEMENT);

        BoatEngine boatEngine = null;

        switch (type) {
            case JET:
                boatEngine = new JetBoatEngine(model, horsepower, displacement);
                break;
            case STERNDRIVE:
                boatEngine = new SterndriveBoatEngine(model, horsepower, displacement);
                break;
        }

        if (boatEngine == null) {
            throw new ArgumentException();
        }

        this.database.saveAndFlush(boatEngine);

        return String.format(BOAT_ENGINE_CREATED_SUCCESSFULLY, model, horsepower, displacement);
    }

    @Override
    public String createRowBoat(String model, Integer weight, Integer oars) throws DuplicateModelException, ArgumentException {
        this.modelCheck(model, BOAT_MODEL_LENGTH);
        this.parameterCheck(weight, WEIGHT);
        this.parameterCheck(oars, OARS);
        Boat boat = new RowBoat(model, weight, oars);
        this.database.saveAndFlush(boat);
        return String.format(BOAT_CREATED_SUCCESSFULLY, ROW_BOAT, model);
    }

    @Override
    public String createSailBoat(String model, Integer weight, Integer sailEfficiency) throws ArgumentException, DuplicateModelException {
        this.modelCheck(model, BOAT_MODEL_LENGTH);
        this.parameterCheck(weight, WEIGHT);
        if (sailEfficiency <= INVALID_VALUE || sailEfficiency > 100) {
            throw new ArgumentException(ILLEGAL_SAILING_EFFICIENCY);
        }

        Boat boat = new SailBoat(model, weight, sailEfficiency);
        this.database.saveAndFlush(boat);
        return String.format(BOAT_CREATED_SUCCESSFULLY, SAIL_BOAT, model);
    }

    @Override
    public String createPowerBoat(String model, Integer weight, String engineOneModel, String engineTwoModel) throws ArgumentException, NonExistentModelException, DuplicateModelException {
        this.modelCheck(model, BOAT_MODEL_LENGTH);
        this.parameterCheck(weight, WEIGHT);
        BoatEngine engineOne = this.database.getBoatEngine(engineOneModel);
        BoatEngine engineTwo = this.database.getBoatEngine(engineTwoModel);
        Boat boat = new PowerBoat(model, weight, engineOne, engineTwo);
        this.database.saveAndFlush(boat);
        return String.format(BOAT_CREATED_SUCCESSFULLY, POWER_BOAT, model);
    }

    @Override
    public String createYacht(String model, Integer weight, String engine, Integer cargoWeight) throws ArgumentException, NonExistentModelException, DuplicateModelException {
        this.modelCheck(model, BOAT_MODEL_LENGTH);
        this.parameterCheck(weight, WEIGHT);
        this.parameterCheck(cargoWeight, CARGO_WEIGHT);
        BoatEngine boatEngine = this.database.getBoatEngine(engine);
        Boat boat = new Yacht(model, weight, boatEngine, cargoWeight);
        this.database.saveAndFlush(boat);
        return String.format(BOAT_CREATED_SUCCESSFULLY, YACHT, model);
    }

    @Override
    public String openRace(Integer distance, Integer windSpeed, Integer currentSpeed, boolean allowsMotorboats) throws RaceAlreadyExistsException, ArgumentException {
        if (this.race != null) {
            throw new RaceAlreadyExistsException();
        }
        this.parameterCheck(distance, DISTANCE);
        this.race = new Race(distance, windSpeed, currentSpeed, allowsMotorboats);
        return String.format(RACE_CREATED_SUCCESSFULLY, distance, windSpeed, currentSpeed);
    }

    @Override
    public String signUpBoat(String model) throws NoSetRaceException, NonExistentModelException, ArgumentException, DuplicateModelException {
        if (this.race == null) {
            throw new NoSetRaceException();
        }
        Boat boat = this.database.getBoat(model);
        this.race.add(boat);
        return String.format(SING_UP_SUCCESSFULLY, model);
    }

    @Override
    public String startRace() throws NoSetRaceException, InsufficientContestantsException {
        if (this.race == null) {
            throw new NoSetRaceException();
        }

        Collection<Boat> boats = this.race.getBoats();

        if (boats.size() < 3) {
            throw new InsufficientContestantsException();
        }

        Boat[] winners = boats
                .stream()
                .sorted((a, b) -> {
                    double boatATime = this.race.getDistance() / a.getSpeed(this.race.getConditions());
                    double boatBTime = this.race.getDistance() / b.getSpeed(this.race.getConditions());
                    if (boatATime <= 0 && boatBTime <= 0) {
                        return 0;
                    }
                    return Double.compare(boatATime, boatBTime);
                })
                .toArray(Boat[]::new);

        this.segregateElements(winners);

        String[] place = {"First", "Second", "Third"};
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            Boat boat = winners[i];
            Double speed = boat.getSpeed(this.race.getConditions());
            String time = speed <= 0 ? "Did not finish!" : String.format("%.2f sec", this.race.getDistance() / speed);
            output.append(String.format("%s place: %s Model: %s Time: %s\n",
                    place[i],
                    boat.getClass().getSimpleName(),
                    boat.getModel(),
                    time));
        }


        this.race = null;
        return output.toString().trim();
    }

    @Override
    public String getStatistics() throws NoSetRaceException {
        if (this.race == null) {
            throw new NoSetRaceException();
        }

        Collection<Boat> boats = this.race.getBoats();

        Map<String, Double> count = new LinkedHashMap<>() {{
            put(POWER_BOAT_CLASS, 0D);
            put(ROW_BOAT_CLASS, 0D);
            put(SAIL_BOAT_CLASS, 0D);
            put(YACHT_BOAT_CLASS, 0D);
        }};

        for (Boat boat : boats) {
            String className = boat.getClass().getSimpleName();
            count.put(className, count.get(className) + 1);
        }

        return count.entrySet()
                .stream()
                .map(entry -> String.format("%s -> %.2f%%", entry.getKey(), (entry.getValue() / boats.size()) * 100.0))
                .collect(Collectors.joining("\n"));
    }

    private void segregateElements(Boat[] boats) {
        Boat[] temp = new Boat[boats.length];
        int j = 0;
        for (Boat boat : boats) {
            if (boat.getSpeed(this.race.getConditions()) > 0) {
                temp[j++] = boat;
            }
        }
        if (j == boats.length || j == 0) return;
        for (Boat boat : boats) {
            if (boat.getSpeed(this.race.getConditions()) <= 0) {
                temp[j++] = boat;
            }
        }
        System.arraycopy(temp, 0, boats, 0, boats.length);
    }

    private void parameterCheck(Integer value, String parameter) throws ArgumentException {
        if (value <= INVALID_VALUE) {
            throw new ArgumentException(String.format(ILLEGAL_PARAMETER, parameter));
        }
    }

    private void modelCheck(String model, int minModelLength) throws ArgumentException {
        if (model.length() < minModelLength) {
            throw new ArgumentException(String.format(ILLEGAL_MODEL_NAME, minModelLength));
        }
    }
}
