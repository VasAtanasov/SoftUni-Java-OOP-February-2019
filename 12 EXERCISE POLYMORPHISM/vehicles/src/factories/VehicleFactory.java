package factories;

import interfaces.Vehicle;
import models.Bus;
import models.Car;
import models.Truck;

import java.util.List;

public final class VehicleFactory {
    public static Vehicle init(List<String> arguments) {
        String type = arguments.get(0);
        if (arguments.size() == 3) {
            return initVehicleWithoutTankCapacity(type, arguments.subList(1, arguments.size()));
        }
        return initVehicleWithTankCapacity(type, arguments.subList(1, arguments.size()));
    }

    private static Vehicle initVehicleWithTankCapacity(String type, List<String> args) {
        double fuelQuantity = Double.parseDouble(args.get(0));
        double fuelConsumption = Double.parseDouble(args.get(1));
        double tankCapacity = Double.parseDouble(args.get(2));

        switch (type) {
            case "Car":
                return new Car(fuelQuantity, fuelConsumption,tankCapacity);
            case "Truck":
                return new Truck(fuelQuantity, fuelConsumption,tankCapacity);
            case "Bus":
                return new Bus(fuelQuantity, fuelConsumption, tankCapacity);
        }
        return null;
    }

    private static Vehicle initVehicleWithoutTankCapacity(String type, List<String> args) {
        double fuelQuantity = Double.parseDouble(args.get(0));
        double fuelConsumption = Double.parseDouble(args.get(1));

        switch (type) {
            case "Car":
                return new Car(fuelQuantity, fuelConsumption);
            case "Truck":
                return new Truck(fuelQuantity, fuelConsumption);
        }
        return null;
    }

}
