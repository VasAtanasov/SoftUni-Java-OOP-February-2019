package models.boats;

import interfaces.Model;
import models.races.Conditions;

public abstract class Boat implements Model {
    private String model;
    private Integer weight;

    protected Boat(String model, Integer weight) {
        this.model = model;
        this.weight = weight;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public abstract Double getSpeed(Conditions conditions);
}
