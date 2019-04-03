package models.engines;

public class JetBoatEngine extends BoatEngine {

    public JetBoatEngine(String model, Integer horsepower, Integer displacement) {
        super(model, horsepower, displacement);
    }

    @Override
    public Double getOutput() {
        return (this.getHorsepower() * 5D) + this.getDisplacement();
    }
}
