package exceptions;

public class InsufficientContestantsException extends Exception {
    public InsufficientContestantsException() {
        super("Not enough contestants for the race.");
    }
}
