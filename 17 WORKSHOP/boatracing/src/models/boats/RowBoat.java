package models.boats;

import models.races.Conditions;

public class RowBoat extends Boat {
    private Integer oars;

    public RowBoat(String model, Integer weight, Integer oars) {
        super(model, weight);
        this.oars = oars;
    }

    //(Oars * 100) - Boat Weight + Race Ocean Current Speed
    @Override
    public Double getSpeed(Conditions conditions) {
        return (this.oars * 100D) - this.getWeight() + conditions.getCurrentSpeed();
    }
}
