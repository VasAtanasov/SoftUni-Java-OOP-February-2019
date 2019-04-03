package models.engines;

import interfaces.Model;

public abstract class BoatEngine implements Model {
    private String model;
    private Integer horsepower;
    private Integer displacement;

    protected BoatEngine(String model, Integer horsepower, Integer displacement) {
        this.model = model;
        this.horsepower = horsepower;
        this.displacement = displacement;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    public Integer getHorsepower() {
        return this.horsepower;
    }

    public Integer getDisplacement() {
        return this.displacement;
    }

    public abstract Double getOutput();
}
