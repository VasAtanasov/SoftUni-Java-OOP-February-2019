package impl;

import contracts.Commando;
import contracts.Mission;

import java.util.Set;
import java.util.stream.Collectors;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando {

    private Set<Mission> missions;

    public CommandoImpl(String id, String firstName, String lastName, double salary, String corps, Set<Mission> missions) {
        super(id, firstName, lastName, salary, corps);
        this.missions = missions;
    }

    private String getMissions() {
        if (this.missions.isEmpty()) {
            return "";
        }
        return System.lineSeparator() +
                this.missions.stream()
                        .map(m -> " " + m.toString())
                        .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String toString() {
        return super.toString() +
                System.lineSeparator() +
                String.format("Corps: %s", super.getCorps()) +
                System.lineSeparator() +
                "Missions:" +
                this.getMissions();
    }
}
