package cresla.entities.reactors;

public class CryoReactor extends BaseReactor {
    private int cryoProductionIndex;

    public CryoReactor(int id, int cryoProductionIndex, int moduleCapacity) {
        super(id, moduleCapacity);
        this.cryoProductionIndex = cryoProductionIndex;
    }

    @Override
    public long getTotalEnergyOutput() {
        return super.getTotalEnergyOutput() * this.cryoProductionIndex;
    }
}
