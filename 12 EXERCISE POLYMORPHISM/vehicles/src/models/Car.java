package models;

public class Car extends BaseVehicle {
    private static final double CAR_AIR_CONDITION_CONSUMPTION = 0.9;

    public Car(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption, CAR_AIR_CONDITION_CONSUMPTION);
    }

    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity, CAR_AIR_CONDITION_CONSUMPTION);
    }
}
