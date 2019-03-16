import java.util.ArrayList;
import java.util.List;

public class SongDatabase {
    private List<Song> songs;

    public SongDatabase() {
        this.songs = new ArrayList<>();
    }

    public String addSong(Song song) {
        this.songs.add(song);
        return "Song added.";
    }

    private int getTotalLength() {
        return this.songs.stream()
                .map(Song::getTotalSeconds)
                .reduce(0, (a, b) -> a + b);
    }

    public String getTotalLengthOfSongs() {
        return this.toString();
    }

    private String calculateTime(long seconds) {
        long sec = seconds % 60;
        long minutes = seconds % 3600 / 60;
        long hours = seconds % 86400 / 3600;
        return String.format("%dh %dm %ds", hours, minutes, sec);
    }

    @Override
    public String toString() {
        return String.format("Songs added: %d%nPlaylist length: %s",
                this.songs.size(),
                this.calculateTime(this.getTotalLength()));
    }
}
