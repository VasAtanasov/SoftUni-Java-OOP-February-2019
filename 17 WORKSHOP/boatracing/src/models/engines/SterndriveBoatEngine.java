package models.engines;

public class SterndriveBoatEngine extends BoatEngine {

    public SterndriveBoatEngine(String model, Integer horsepower, Integer displacement) {
        super(model, horsepower, displacement);
    }

    @Override
    public Double getOutput() {
        return (this.getHorsepower() * 7D) + this.getDisplacement();
    }
}
