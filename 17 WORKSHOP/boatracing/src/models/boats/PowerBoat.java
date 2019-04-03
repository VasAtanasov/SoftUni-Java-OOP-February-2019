package models.boats;

import models.engines.BoatEngine;
import models.races.Conditions;

public class PowerBoat extends Boat {
    private BoatEngine engineOne;
    private BoatEngine engineTwo;

    public PowerBoat(String model, Integer weight, BoatEngine engineOne, BoatEngine engineTwo) {
        super(model, weight);
        this.engineOne = engineOne;
        this.engineTwo = engineTwo;
    }

    //(Engine 1 Output + Engine 2 Output) - Boat’s Weight + (Race Ocean Current Speed / 5);
    @Override
    public Double getSpeed(Conditions conditions) {
        return (engineOne.getOutput() + engineTwo.getOutput()) - this.getWeight() + (conditions.getCurrentSpeed() / 5D);
    }
}
