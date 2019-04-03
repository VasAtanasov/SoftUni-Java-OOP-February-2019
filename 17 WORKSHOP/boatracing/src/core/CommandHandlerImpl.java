package core;

import exceptions.*;
import interfaces.CommandHandler;
import interfaces.Controller;

public class CommandHandlerImpl implements CommandHandler {

    private enum Commands {
        CreateBoatEngine,
        CreatePowerBoat,
        CreateRowBoat,
        CreateSailBoat,
        CreateYacht,
        GetStatistic,
        OpenRace,
        SignUpBoat,
        StartRace
    }

    private Controller controller;

    public CommandHandlerImpl(Controller controller) {
        this.controller = controller;
    }

    @Override
    public String interpretCommand(String[] data) throws
            DuplicateModelException,
            ArgumentException,
            NonExistentModelException,
            RaceAlreadyExistsException,
            NoSetRaceException,
            InsufficientContestantsException {

        Commands command = Commands.valueOf(data[0]);

        switch (command) {
            case CreateBoatEngine:
                return this.createBoatEngine(data);
            case CreatePowerBoat:
                return this.createPowerBoat(data);
            case CreateRowBoat:
                return this.createRowBoat(data);
            case CreateSailBoat:
                return this.createSailBoat(data);
            case CreateYacht:
               return this.createYacht(data);
            case GetStatistic:
                return this.getStatistics();
            case OpenRace:
                return this.openRace(data);
            case SignUpBoat:
                return this.signUpBoat(data);
            case StartRace:
               return this.startRace();
        }

        throw new ArgumentException();
    }

    private String startRace() throws NoSetRaceException, InsufficientContestantsException {
        return this.controller.startRace();
    }

    private String signUpBoat(String[] data) throws DuplicateModelException, NoSetRaceException, NonExistentModelException, ArgumentException {
        String model = data[1];
        return this.controller.signUpBoat(model);
    }

    private String openRace(String[] data) throws ArgumentException, RaceAlreadyExistsException {
        Integer distance = Integer.parseInt(data[1]);
        Integer windSpeed = Integer.parseInt(data[2]);
        Integer currentSpeed = Integer.parseInt(data[3]);
        boolean allowsMotorboats = Boolean.parseBoolean(data[4]);
        return this.controller.openRace(distance, windSpeed, currentSpeed, allowsMotorboats);
    }

    private String getStatistics() throws NoSetRaceException {
        return this.controller.getStatistics();
    }

    private String createYacht(String[] data) throws ArgumentException, NonExistentModelException, DuplicateModelException {
        String model = data[1];
        Integer weight = Integer.parseInt(data[2]);
        String engine = data[3];
        Integer cargoWeight = Integer.parseInt(data[4]);
        return this.controller.createYacht(model,weight,engine,cargoWeight);
    }

    private String createSailBoat(String[] data) throws ArgumentException, DuplicateModelException {
        String model = data[1];
        Integer weight = Integer.parseInt(data[2]);
        Integer sailEfficiency = Integer.parseInt(data[3]);
        return this.controller.createSailBoat(model, weight, sailEfficiency);
    }

    private String createRowBoat(String[] data) throws DuplicateModelException, ArgumentException {
        String model = data[1];
        Integer weight = Integer.parseInt(data[2]);
        Integer oars = Integer.parseInt(data[3]);
        return this.controller.createRowBoat(model, weight, oars);
    }

    private String createPowerBoat(String[] data) throws ArgumentException, NonExistentModelException, DuplicateModelException {
        String model = data[1];
        Integer weight = Integer.parseInt(data[2]);
        String engineOneModel = data[3];
        String engineTwoModel = data[4];
        return this.controller.createPowerBoat(model, weight, engineOneModel, engineTwoModel);
    }

    private String createBoatEngine(String[] data) throws DuplicateModelException, ArgumentException {
        String model = data[1];
        Integer horsepower = Integer.parseInt(data[2]);
        Integer displacement = Integer.parseInt(data[3]);
        String type = data[4];
        return controller.createBoatEngine(model, horsepower, displacement, type);
    }
}
