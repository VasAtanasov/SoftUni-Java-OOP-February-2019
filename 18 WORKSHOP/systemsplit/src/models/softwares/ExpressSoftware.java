package models.softwares;

public class ExpressSoftware extends Software {
    private static final long MEMORY_CONSUMPTION_INCREASE_INDEX = 2;
    private static final String EXPRESS = "Express";

    public ExpressSoftware(String name, long capacityConsumption, long memoryConsumption) {
        super(name, EXPRESS, capacityConsumption, memoryConsumption);
    }

    @Override
    protected void setMemoryConsumption(long memoryConsumption) {
        super.setMemoryConsumption(memoryConsumption * MEMORY_CONSUMPTION_INCREASE_INDEX);
    }
}
