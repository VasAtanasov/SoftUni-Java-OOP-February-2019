package models.softwares;

import models.ModelImpl;

public abstract class Software extends ModelImpl {

    private long capacityConsumption;
    private long memoryConsumption;

    protected Software(String name, String type, long capacityConsumption, long memoryConsumption) {
        super(name, type);
        this.setCapacityConsumption(capacityConsumption);
        this.setMemoryConsumption(memoryConsumption);
    }

    public long getCapacityConsumption() {
        return this.capacityConsumption;
    }

    public long getMemoryConsumption() {
        return this.memoryConsumption;
    }

    protected void setCapacityConsumption(long capacityConsumption) {
        this.capacityConsumption = capacityConsumption;
    }

    protected void setMemoryConsumption(long memoryConsumption) {
        this.memoryConsumption = memoryConsumption;
    }
}
