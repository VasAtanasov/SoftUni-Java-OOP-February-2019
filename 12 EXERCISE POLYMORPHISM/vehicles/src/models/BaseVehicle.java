package models;

import interfaces.Vehicle;

public abstract class BaseVehicle implements Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;
    private double airConditionConsumption;
    private boolean isAirConditionWorking;

    protected BaseVehicle(double fuelQuantity, double fuelConsumption, double airConditionConsumption) {
        this(fuelQuantity, fuelConsumption, Double.MAX_VALUE, airConditionConsumption);
    }

    protected BaseVehicle(double fuelQuantity, double fuelConsumption, double tankCapacity, double airConditionConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
        this.airConditionConsumption = airConditionConsumption;
        this.isAirConditionWorking = true;
    }

    @Override
    public double getTankCapacity() {
        return this.tankCapacity;
    }

    @Override
    public double getFuelQuantity() {
        return this.fuelQuantity;
    }

    @Override
    public boolean drive(double distance) {
        double consumption = this.fuelConsumption;
        if (this.isAirConditionWorking) {
            consumption += this.airConditionConsumption;
        }
        double fuelNeeded = consumption * distance;
        if (fuelNeeded > this.fuelQuantity) {
            return false;
        }
        this.fuelQuantity -= fuelNeeded;
        return true;
    }

    @Override
    public void refuel(double fuel) {
        this.fuelQuantity += fuel;
    }

    @Override
    public void switchOnAirCon() {
        this.isAirConditionWorking = true;
    }

    @Override
    public void switchOffAirCon() {
        this.isAirConditionWorking = false;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.fuelQuantity);
    }
}

