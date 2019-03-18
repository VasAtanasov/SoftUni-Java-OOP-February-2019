import factories.VehicleFactory;
import interfaces.Vehicle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static Map<String, Vehicle> vehicles;
    private static BufferedReader reader;
    private static DecimalFormat df;

    static {
        vehicles = new LinkedHashMap<>();
        reader = new BufferedReader(new InputStreamReader(System.in));
        df = new DecimalFormat("0.##");
    }

    public static void main(String[] args) throws IOException {

        String input;
        while (true) {
            input = reader.readLine();
            if (input.matches("[0-9]+")) {
                break;
            }
            List<String> arguments = getArgs(input);
            Vehicle vehicle = VehicleFactory.init(arguments);
            vehicles.putIfAbsent(arguments.get(0), vehicle);
        }

        int n = Integer.parseInt(input);

        for (int i = 0; i < n; i++) {
            List<String> tokens = getArgs(reader.readLine());
            String command = tokens.get(0);
            String type = tokens.get(1);
            double value = Double.parseDouble(tokens.get(2));

            switch (command) {
                case "Drive":
                    driveVehicle(vehicles.get(type), value);
                    break;
                case "Refuel":
                    refileVehicle(vehicles.get(type), value);
                    break;
                case "DriveEmpty":
                    driveEmptyBus(vehicles.get(type), value);
                    break;
            }
        }

        printVehicles();
    }

    private static void driveEmptyBus(Vehicle vehicle, double value) {
        if (vehicle == null) {
            return;
        }
        vehicle.switchOffAirCon();
        driveVehicle(vehicle, value);
    }

    private static void printVehicles() {
        for (Vehicle vehicle : vehicles.values()) {
            System.out.println(vehicle);
        }
    }

    private static void refileVehicle(Vehicle vehicle, double quantity) {
        if (quantity <= 0) {
            System.out.println("Fuel must be a positive number");
            return;
        }

        if (vehicle.getTankCapacity() < quantity + vehicle.getFuelQuantity()) {
            System.out.println("Cannot fit fuel in tank");
            return;
        }
        vehicle.refuel(quantity);
    }

    private static void driveVehicle(Vehicle vehicle, double distance) {
        if (vehicle == null) {
            return;
        }
        if (vehicle.drive(distance)) {
            System.out.println(String.format("%s travelled %s km", vehicle.getClass().getSimpleName(), df.format(distance)));
        } else {
            System.out.println(String.format("%s needs refueling", vehicle.getClass().getSimpleName()));
        }
    }

    private static List<String> getArgs(String input) {
        return Arrays.stream(input.split("\\s+"))
                .collect(Collectors.toList());
    }
}
