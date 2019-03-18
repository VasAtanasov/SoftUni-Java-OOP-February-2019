package interfaces;

public interface Vehicle extends Coolable {
    double getTankCapacity();

    double getFuelQuantity();

    boolean drive(double distance);

    void refuel(double fuel);
}
