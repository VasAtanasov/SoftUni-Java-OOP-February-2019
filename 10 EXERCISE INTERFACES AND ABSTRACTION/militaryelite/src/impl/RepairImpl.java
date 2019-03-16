package impl;

import contracts.Repair;

public class RepairImpl implements Repair {
    private String partName;
    private int hours;

    public RepairImpl(String partName, int hours) {
        this.partName = partName;
        this.hours = hours;
    }

    @Override
    public String toString() {
        return String.format("Part Name: %s Hours Worked: %d", this.partName, this.hours);
    }
}
