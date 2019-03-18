package models;

public class Bus extends BaseVehicle {
    private static final double CAR_AIR_CONDITION_CONSUMPTION = 1.4;

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity, CAR_AIR_CONDITION_CONSUMPTION);
    }
}
