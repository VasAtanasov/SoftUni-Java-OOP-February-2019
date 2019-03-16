package impl;

import contracts.LeutenantGeneral;
import contracts.Private;

import java.util.Set;
import java.util.stream.Collectors;

public class LeutenantGeneralImpl extends PrivateImpl implements LeutenantGeneral {
    private Set<Private> privates;

    public LeutenantGeneralImpl(String id, String firstName, String lastName, double salary, Set<Private> privates) {
        super(id, firstName, lastName, salary);
        this.privates = privates;
    }

    private String getPrivates() {
        if (this.privates.isEmpty()) {
            return "";
        }
        return System.lineSeparator() +
                this.privates.stream()
                        .sorted((a, b) -> b.getId().compareTo(a.getId()))
                        .map(p -> "  " + p.toString())
                        .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String toString() {
        return super.toString() +
                System.lineSeparator() +
                "Privates:" +
                this.getPrivates();
    }
}
