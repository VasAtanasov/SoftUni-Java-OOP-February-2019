package models;

public class Truck extends BaseVehicle {
    private static final double TRUCK_AIR_CONDITION_CONSUMPTION = 1.6;

    private static final int TRUCK_REFILE_FACTOR = 95;

    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption, TRUCK_AIR_CONDITION_CONSUMPTION);
    }

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity, TRUCK_AIR_CONDITION_CONSUMPTION);
    }

    @Override
    public void refuel(double fuel) {
        super.refuel((fuel * TRUCK_REFILE_FACTOR) / 100);
    }
}
