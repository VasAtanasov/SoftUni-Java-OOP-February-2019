package exeptions;

public class InvalidSongNameException extends InvalidSongException {
    public InvalidSongNameException(String message) {
        super(message);
    }
}
