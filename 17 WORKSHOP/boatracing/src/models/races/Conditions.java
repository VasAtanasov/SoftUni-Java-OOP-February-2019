package models.races;

public class Conditions {
    private Integer windSpeed;
    private Integer currentSpeed;

    public Conditions(Integer windSpeed, Integer currentSpeed) {
        this.windSpeed = windSpeed;
        this.currentSpeed = currentSpeed;
    }

    public Integer getWindSpeed() {
        return this.windSpeed;
    }

    public Integer getCurrentSpeed() {
        return this.currentSpeed;
    }
}
