package models.softwares;

public class LightSoftware extends Software {
    private static final long MEMORY_CONSUMPTION_DECREASE_INDEX = 50;
    private static final long CAPACITY_CONSUMPTION_INCREASE_INDEX = 50;
    private static final String LIGHT = "Light";


    public LightSoftware(String name, long capacityConsumption, long memoryConsumption) {
        super(name, LIGHT, capacityConsumption, memoryConsumption);
    }

    @Override
    protected void setCapacityConsumption(long capacityConsumption) {
        super.setCapacityConsumption(capacityConsumption + ((capacityConsumption * CAPACITY_CONSUMPTION_INCREASE_INDEX) / MAX_INDEX));
    }

    @Override
    protected void setMemoryConsumption(long memoryConsumption) {
        super.setMemoryConsumption(memoryConsumption - ((memoryConsumption * MEMORY_CONSUMPTION_DECREASE_INDEX) / MAX_INDEX));
    }
}
