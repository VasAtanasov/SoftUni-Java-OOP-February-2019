package cresla.entities.reactors;

public class HeatReactor extends BaseReactor {
    private int heatReductionIndex;

    public HeatReactor(int id, int heatReductionIndex, int moduleCapacity) {
        super(id, moduleCapacity);
        this.heatReductionIndex = heatReductionIndex;
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return super.getTotalHeatAbsorbing() + this.heatReductionIndex;
    }
}
