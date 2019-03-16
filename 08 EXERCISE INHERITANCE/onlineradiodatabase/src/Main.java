import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader reader;
    private static SongDatabase songDatabase;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        songDatabase = new SongDatabase();
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(";");
            if (tokens.length != 3) {
             continue;
            }
            String name = tokens[0];
            String title = tokens[1];
            String length = tokens[2];

            try {
                Song song = new Song(name, title, length);
                System.out.println(songDatabase.addSong(song));
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }

        System.out.println(songDatabase);
    }
}
