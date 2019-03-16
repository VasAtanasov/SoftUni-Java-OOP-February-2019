package impl;

import contracts.Mission;

public class MissionImpl implements Mission {
    private static final String IN_PROGRESS = "inProgress";
    private static final String FINISHED = "Finished";

    private String codeName;
    private String state;

    public MissionImpl(String codeName, String state) {
        this.codeName = codeName;
        this.setState(state);
    }

    private void setState(String state) {
        if (! IN_PROGRESS.equals(state) && ! FINISHED.equals(state)) {
            throw new IllegalArgumentException();
        }

        this.state = state;
    }

    @Override
    public void completeMission() {
        this.state = FINISHED;
    }

    @Override
    public String toString() {
        return String.format("Code Name: %s State: %s", this.codeName, this.state);
    }
}
