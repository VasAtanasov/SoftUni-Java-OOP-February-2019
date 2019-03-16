package impl;

import contracts.Engineer;
import contracts.Repair;

import java.util.Set;
import java.util.stream.Collectors;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer {
    private Set<Repair> repairs;

    public EngineerImpl(String id, String firstName, String lastName, double salary, String corps, Set<Repair> repairs) {
        super(id, firstName, lastName, salary, corps);
        this.repairs = repairs;
    }

    private String getRepairs() {
        if (this.repairs.isEmpty()) {
            return "";
        }
        return System.lineSeparator() +
                this.repairs.stream()
                        .map(r -> " " + r.toString())
                        .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String toString() {
        return super.toString() +
                System.lineSeparator() +
                String.format("Corps: %s", super.getCorps()) +
                System.lineSeparator() +
                "Repairs:" +
                this.getRepairs();
    }
}
