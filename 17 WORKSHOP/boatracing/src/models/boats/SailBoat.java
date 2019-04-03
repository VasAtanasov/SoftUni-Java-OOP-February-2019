package models.boats;

import models.races.Conditions;

public class SailBoat extends Boat {
    private Integer sailEfficiency;

    public SailBoat(String model, Integer weight, Integer sailEfficiency) {
        super(model, weight);
        this.sailEfficiency = sailEfficiency;
    }

    //(Race Wind Speed * (Boat Sail Efficiency / 100)) – Boat’s Weight + (Race Ocean Current Speed / 2)
    @Override
    public Double getSpeed(Conditions conditions) {
        return (conditions.getWindSpeed() * (this.sailEfficiency / 100D)) - this.getWeight() + (conditions.getCurrentSpeed() / 2D);
    }
}
