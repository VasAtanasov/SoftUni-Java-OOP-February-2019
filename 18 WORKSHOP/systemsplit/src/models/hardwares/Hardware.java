package models.hardwares;

import models.ModelImpl;
import models.softwares.Software;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.ToLongFunction;

public abstract class Hardware extends ModelImpl {

    private long maxCapacity;
    private long maxMemory;
    private Map<String, Software> components;

    protected Hardware(String name, String type) {
        super(name, type);
        this.components = new LinkedHashMap<>();
    }

    protected Hardware(String name, String type, long maxCapacity, long maxMemory) {
        this(name, type);
        this.setMaxCapacity(maxCapacity);
        this.setMaxMemory(maxMemory);
    }

    public long getMaxCapacity() {
        return this.maxCapacity;
    }

    public long getMaxMemory() {
        return this.maxMemory;
    }

    public Collection<Software> getComponents() {
        return Collections.unmodifiableCollection(this.components.values());
    }

    public int getInstalledSoftwaresCount() {
        return this.components.size();
    }

    public long getAvailableMemory() {
        return this.maxMemory - this.getOccupiedMemory();
    }

    public long getAvailableCapacity() {
        return this.maxCapacity - this.getOccupiedCapacity();
    }

    public boolean install(Software software) {
        long freeCapacity = this.getAvailableCapacity();
        if (freeCapacity - software.getCapacityConsumption() < 0) {
            return false;
        }
        long freeMemory = this.getAvailableMemory();
        if (freeMemory - software.getMemoryConsumption() < 0) {
            return false;
        }
        this.components.put(software.getName(), software);
        return true;
    }

    public long getOccupiedCapacity() {
        return this.sumValues(Software::getCapacityConsumption);
    }

    public long getOccupiedMemory() {
        return this.sumValues(Software::getMemoryConsumption);
    }

    public void uninstall(String softwareComponentName) {
        this.components.remove(softwareComponentName);
    }

    protected void setMaxCapacity(long maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    protected void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }

    private long sumValues(ToLongFunction<Software> function) {
        return this.components.values()
                .stream()
                .mapToLong(function)
                .sum();
    }
}
