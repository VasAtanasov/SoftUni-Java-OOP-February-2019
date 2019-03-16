package impl;

import contracts.SpecialisedSoldier;

public abstract class SpecialisedSoldierImpl extends PrivateImpl implements SpecialisedSoldier {
    private String corps;

    private static final String AIR_FORCES = "Airforces";
    private static final String MARINES = "Marines";

    protected SpecialisedSoldierImpl(String id, String firstName, String lastName, double salary, String corps) {
        super(id, firstName, lastName, salary);
        this.setCorps(corps);
    }

    @Override
    public String getCorps() {
        return this.corps;
    }

    private void setCorps(String corps) {
        if (! AIR_FORCES.equals(corps) && ! MARINES.equals(corps)) {
            throw new IllegalArgumentException();
        }

        this.corps = corps;
    }
}
