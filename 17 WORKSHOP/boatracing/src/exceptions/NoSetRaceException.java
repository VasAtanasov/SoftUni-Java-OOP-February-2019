package exceptions;

public class NoSetRaceException extends Exception {
    public NoSetRaceException() {
        super("There is currently no race set.");
    }
}
