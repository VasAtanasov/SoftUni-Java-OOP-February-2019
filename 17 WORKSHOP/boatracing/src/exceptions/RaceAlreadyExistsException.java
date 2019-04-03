package exceptions;

public class RaceAlreadyExistsException extends Exception {
    public RaceAlreadyExistsException() {
        super("The current race has already been set.");
    }
}
