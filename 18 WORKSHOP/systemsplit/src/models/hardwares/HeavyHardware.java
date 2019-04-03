package models.hardwares;

public class HeavyHardware extends Hardware {
    private static final long CAPACITY_INCREASE_INDEX = 2;
    private static final long MEMORY_DECREASE_INDEX = 25;
    private static final String HEAVY = "Heavy";

    public HeavyHardware(String name, long maxCapacity, long maxMemory) {
        super(name, HEAVY, maxCapacity, maxMemory);
    }

    @Override
    protected void setMaxCapacity(long maxCapacity) {
        super.setMaxCapacity(maxCapacity * CAPACITY_INCREASE_INDEX);
    }

    @Override
    protected void setMaxMemory(long maxMemory) {
        super.setMaxMemory(maxMemory - ((maxMemory * MEMORY_DECREASE_INDEX) / MAX_INDEX));
    }
}
