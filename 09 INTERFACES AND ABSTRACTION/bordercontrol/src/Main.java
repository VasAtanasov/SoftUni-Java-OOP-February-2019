import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    private static final String END_COMMAND = "End";

    private static Map<String, Identifiable> byId;
    private static Set<String> detained;
    private static BufferedReader reader;


    static {
        detained = new LinkedHashSet<>();
        byId = new LinkedHashMap<>();
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (!END_COMMAND.equals(input = reader.readLine())) {
            String[] tokens = input.split("\\s+");

            Identifiable identifiable;
            if (tokens.length == 3) {
                identifiable = initCitizen(tokens);
            } else {
                identifiable = initRobot(tokens);
            }
            byId.put(identifiable.getId(), identifiable);
        }

        String endDigits = reader.readLine();

        for (String id : byId.keySet()) {
            if (id.endsWith(endDigits)) {
                detained.add(id);
            }
        }

        detained.forEach(System.out::println);
    }

    private static Identifiable initRobot(String[] tokens) {
        return new Robot(tokens[0], tokens[1]);
    }

    private static Identifiable initCitizen(String[] tokens) {
        return new Citizen(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
    }
}
