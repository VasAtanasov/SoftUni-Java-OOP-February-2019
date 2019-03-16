import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static BufferedReader reader;

    private static Gandalf GandalfTheWhite;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));

        GandalfTheWhite = new Gandalf();
    }

    public static void main(String[] args) throws IOException {
        Arrays.stream(reader.readLine().toLowerCase().trim().split("\\s+")).forEach(food -> {
            GandalfTheWhite.eatFood(food);
        });
        System.out.println(GandalfTheWhite.getFoodPoints());
        System.out.println(GandalfTheWhite.getMood());
    }
}


