package models.boats;

import models.engines.BoatEngine;
import models.races.Conditions;

public class Yacht extends Boat {
    private BoatEngine engine;
    private Integer cargoWeight;

    public Yacht(String model, Integer weight, BoatEngine engine, Integer cargoWeight) {
        super(model, weight);
        this.engine = engine;
        this.cargoWeight = cargoWeight;
    }

    //Boat Engine Output - (Boat Weight + Cargo Weight) + (Race Ocean Current Speed / 2);
    @Override
    public Double getSpeed(Conditions conditions) {
        return this.engine.getOutput() - (this.getWeight() + this.cargoWeight) + (conditions.getCurrentSpeed() / 2D);
    }
}
