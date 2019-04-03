package models.races;

import exceptions.ArgumentException;
import exceptions.DuplicateModelException;
import models.boats.Boat;

import java.util.*;

public class Race {
    private static final String ILLEGAL_BOAT_TYPE = "The specified boat does not meet the race constraints.";
    private static final List<String> motorBoats = Arrays.asList("PowerBoat", "Yacht");

    private Integer distance;
    private Conditions conditions;
    private Map<String, Boat> boats;
    private boolean allowMotorboats;

    public Race() {
        this.boats = new LinkedHashMap<>();
    }

    public Race(Integer distance, Integer windSpeed, Integer currentSpeed, boolean allowMotorboats) {
        this();
        this.distance = distance;
        this.conditions = new Conditions(windSpeed, currentSpeed);
        this.allowMotorboats = allowMotorboats;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public Conditions getConditions() {
        return this.conditions;
    }

    public void add(Boat boat) throws DuplicateModelException, ArgumentException {
        if (! this.allowMotorboats && this.isMotorBoat(boat)) {
            throw new ArgumentException(ILLEGAL_BOAT_TYPE);
        }
        if (this.boats.containsKey(boat.getModel())) {
            throw new DuplicateModelException();
        }
        this.boats.put(boat.getModel(), boat);
    }

    public Collection<Boat> getBoats() {
        return Collections.unmodifiableCollection(this.boats.values());
    }

    private boolean isMotorBoat(Boat boat) {
        return motorBoats.contains(boat.getClass().getSimpleName());
    }
}
