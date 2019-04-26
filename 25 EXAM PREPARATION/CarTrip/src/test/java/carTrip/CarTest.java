package carTrip;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {

    private static final String MODEL = "VOLGA";
    private static final double TANK_CAPACITY = 100D;
    private static final double FUEL_AMOUNT = 50D;
    private static final double FUEL_CONSUMPTION_PER_KM = 0.2D;
    private static final double DELTA = 0.000001D;

    private static final double BIG_DISTANCE = 2123.3;
    private static final double REACHABLE_DISTANCE = 100.0D;

    private static final String INVALID_MODEL = null;
    private static final String EMPTY_MODEL = "";
    private static final double INVALID_FUEL_CONSUMPTION_PER_KM = - 4.0D;
    private static final double INVALID_FUEL_AMOUNT = 100.4D;

    private Car car;

    @Before
    public void setUp() {
        this.car = new Car(MODEL, TANK_CAPACITY, FUEL_AMOUNT, FUEL_CONSUMPTION_PER_KM);
    }

    @Test
    public void constructor_shouldBuildCorrectly() {
        assertEquals(MODEL, this.car.getModel());
        assertEquals(TANK_CAPACITY, this.car.getTankCapacity(), DELTA);
        assertEquals(FUEL_AMOUNT, this.car.getFuelAmount(), DELTA);
        assertEquals(FUEL_CONSUMPTION_PER_KM, this.car.getFuelConsumptionPerKm(), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setModel_shouldThrowOnInvalidData() {
        this.car.setModel(INVALID_MODEL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setModel_shouldThrowIfModelIsEmptyString() {
        this.car.setModel(EMPTY_MODEL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setFuelAmount_shouldThrowOnInvalidData() {
        this.car.setFuelAmount(INVALID_FUEL_AMOUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setFuelConsumptionPerKm_shouldThrowOnInvalidData() {
        this.car.setFuelConsumptionPerKm(INVALID_FUEL_CONSUMPTION_PER_KM);
    }

    @Test(expected = IllegalStateException.class)
    public void drive_shouldThrowOnBigDistance() {
        this.car.drive(BIG_DISTANCE);
    }

    @Test
    public void fuelAmount_shouldChangeOnReachableDistance() {
        double expectedFuelAmount = this.car.getFuelAmount() - (REACHABLE_DISTANCE * this.car.getFuelConsumptionPerKm());
        this.car.drive(REACHABLE_DISTANCE);
        assertEquals(this.car.getFuelAmount(), expectedFuelAmount, DELTA);
    }

    @Test
    public void fuelAmount_shouldReturnMessage() {
        assertEquals(this.car.drive(REACHABLE_DISTANCE), "Have a nice trip");
    }

    @Test(expected = IllegalStateException.class)
    public void refuel_shouldThrowOnInvalidAmount() {
        this.car.refuel(INVALID_FUEL_AMOUNT);
    }

    @Test
    public void refuel_shouldWorkCorrectWithValidFuelAmount() {
        this.car.refuel(FUEL_AMOUNT);
        assertEquals(this.car.getFuelAmount(), TANK_CAPACITY, DELTA);
    }
}