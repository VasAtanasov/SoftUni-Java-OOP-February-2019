public class Ferrari implements Car {
    private static final String MODEL_488_SPIDER = "488-Spider";

    private String model;
    private String driverName;

    public Ferrari(String driverName) {
        this.model = MODEL_488_SPIDER;
        this.driverName = driverName;
    }

    public String getModel() {
        return this.model;
    }

    public String getDriverName() {
        return this.driverName;
    }

    @Override
    public String brakes() {
        return "Brakes!";
    }

    @Override
    public String gas() {
        return "Zadu6avam sA!";
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s/%s", this.model, this.brakes(), this.gas(), this.driverName);
    }
}
