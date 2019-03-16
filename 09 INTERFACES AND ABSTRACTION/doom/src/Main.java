import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        String[] tokens = reader.readLine().split(" \\| ");

        String username = tokens[0];
        String characterType = tokens[1];
        String specialPoints = tokens[2];
        int level = Integer.parseInt(tokens[3]);

        switch (characterType) {
            case "Demon":
                GameObject demon = new Demon(username, level, Double.parseDouble(specialPoints));
                System.out.println(demon);
                break;
            case "Archangel":
                GameObject archangel = new Archangel(username, level, Integer.parseInt(specialPoints));
                System.out.println(archangel);
                break;
        }
    }
}
