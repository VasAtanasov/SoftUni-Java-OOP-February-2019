import java.util.Iterator;

public class TrafficLightImpl implements Iterator<TrafficLights> {

    private TrafficLights currentLight;

    public TrafficLightImpl(TrafficLights currentLight) {
        this.currentLight = currentLight;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public TrafficLights next() {
        switch (this.currentLight.name()) {
            case "RED":
                this.currentLight = TrafficLights.valueOf("GREEN");
                break;
            case "GREEN":
                this.currentLight = TrafficLights.valueOf("YELLOW");
                break;
            default:
                this.currentLight = TrafficLights.valueOf("RED");
                break;
        }
        return this.currentLight;
    }
}
