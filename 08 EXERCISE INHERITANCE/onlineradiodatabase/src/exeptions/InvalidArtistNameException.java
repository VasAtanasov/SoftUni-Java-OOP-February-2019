package exeptions;

public class InvalidArtistNameException extends InvalidSongException {
    public InvalidArtistNameException(String message) {
        super(message);
    }
}
