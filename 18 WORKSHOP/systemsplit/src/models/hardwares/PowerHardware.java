package models.hardwares;

public class PowerHardware extends Hardware {
    private static final long CAPACITY_DECREASE_INDEX = 75;
    private static final long MEMORY_INCREASE_INDEX = 75;
    private static final String POWER = "Power";

    public PowerHardware(String name, long maxCapacity, long maxMemory) {
        super(name, POWER, maxCapacity, maxMemory);
    }

    @Override
    protected void setMaxCapacity(long maxCapacity) {
        super.setMaxCapacity(maxCapacity - ((maxCapacity * CAPACITY_DECREASE_INDEX) / MAX_INDEX));
    }

    @Override
    protected void setMaxMemory(long maxMemory) {
        super.setMaxMemory(maxMemory + ((maxMemory * MEMORY_INCREASE_INDEX) / MAX_INDEX));
    }
}
