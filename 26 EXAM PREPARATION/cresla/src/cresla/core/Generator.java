package cresla.core;

public final class Generator {
    private static int id = 1;

    private Generator() {

    }

    public static int generateId() {
        return id++;
    }
}
