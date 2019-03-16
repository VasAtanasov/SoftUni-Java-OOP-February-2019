import exeptions.InvalidArtistNameException;
import exeptions.InvalidSongNameException;

public class Song {
    private String artistName;
    private String songName;
    private SongLength length;

    public Song(String artistName, String songName, String length) {
        this.setArtistName(artistName);
        this.setSongName(songName);
        this.length = new SongLength(length);
    }

    private void setArtistName(String artistName) {
        if (artistName.length() < 3 || artistName.length() > 20) {
            throw new InvalidArtistNameException(Constants.INVALID_ARTIST_NAME);
        }
        this.artistName = artistName;
    }

    private void setSongName(String songName) {
        if (songName.length() < 3 || songName.length() > 30) {
            throw new InvalidSongNameException(Constants.INVALID_SONG_NAME);
        }
        this.songName = songName;
    }

    public int getTotalSeconds() {
        return this.length.getTotalSeconds();
    }
}
