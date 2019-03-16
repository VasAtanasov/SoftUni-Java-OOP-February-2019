import exeptions.InvalidSongLengthException;
import exeptions.InvalidSongMinutesException;
import exeptions.InvalidSongSecondsException;

public class SongLength {
    private int totalSeconds;

    public SongLength(String length) {
        this.parseSongLength(length);
    }

    private void parseSongLength(String length) {
        String[] tokens = length.split(":");
        if (tokens.length != 2 || !tokens[0].matches("\\d+") || !tokens[1].matches("\\d+")) {
            throw new InvalidSongLengthException(Constants.INVALID_SONG_LENGTH);
        }
        int minutes = Integer.parseInt(tokens[0]);
        int seconds = Integer.parseInt(tokens[1]);
        int totalSeconds = seconds + (minutes * 60);
        if (minutes < 0 || minutes > 14) {
            throw new InvalidSongMinutesException(Constants.INVALID_SONG_MINUTES);
        }
        if (seconds < 0 || seconds > 59) {
            throw new InvalidSongSecondsException(Constants.INVALID_SONG_SECONDS);
        }
        this.totalSeconds = totalSeconds;
    }

    int getTotalSeconds() {
        return this.totalSeconds;
    }
}
