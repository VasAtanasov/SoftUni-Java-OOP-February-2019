import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    private static BufferedReader reader;
    private static Map<String, Team> teams;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        teams = new LinkedHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (! "END".equals(input = reader.readLine())) {
            String[] tokens = input.split(";");
            String command = tokens[0];
            switch (command) {
                case "Team":
                    addTeam(tokens);
                    break;
                case "Add":
                    addPlayer(tokens);
                    break;
                case "Remove":
                    removePlayer(tokens);
                    break;
                case "Rating":
                    printStats(tokens);
                    break;
            }
        }
    }

    private static void printStats(String[] tokens) {
        String teamName = tokens[1];
        if (! teams.containsKey(teamName)) {
            System.out.println(String.format("Team %s does not exist.", teamName));
            return;
        }

        if (isInvalidName(teamName)) {
            System.out.println("A name should not be empty.");
            return;
        }

        System.out.println(teams.get(teamName));
    }

    private static void removePlayer(String[] tokens) {
        String teamName = tokens[1];
        String playerName = tokens[2];
        if (teams.containsKey(teamName)) {

            if (isInvalidName(teamName) || isInvalidName(playerName)) {
                System.out.println("A name should not be empty.");
                return;
            }

            if (! teams.get(teamName).remove(playerName)) {
                System.out.println(String.format("Player %s is not in %s team.", playerName, teamName));
            }
        } else {
            System.out.println(String.format("Team %s does not exist.", teamName));
        }
    }

    private static void addPlayer(String[] tokens) {
        String teamName = tokens[1];
        String playerName = tokens[2];

        if (teams.containsKey(teamName)) {
            if (isInvalidName(teamName) || isInvalidName(playerName)) {
                System.out.println("A name should not be empty.");
                return;
            }

            Map<String, Double> stats = new HashMap<>() {{
                put("Endurance", Double.parseDouble(tokens[3]));
                put("Spring", Double.parseDouble(tokens[4]));
                put("Dribble", Double.parseDouble(tokens[5]));
                put("Passing", Double.parseDouble(tokens[6]));
                put("Shooting", Double.parseDouble(tokens[7]));
            }};

            for (Map.Entry<String, Double> playerStats : stats.entrySet()) {
                if (playerStats.getValue() < 0 || playerStats.getValue() > 100) {
                    System.out.println(String.format("%s should be between 0 and 100.", playerStats.getKey()));
                    return;
                }
            }

            teams.get(teamName).add(playerName, new Player(playerName, stats));
        } else {
            System.out.println(String.format("Team %s does not exist.", teamName));
        }
    }

    private static void addTeam(String[] tokens) {
        String name = tokens[1];
        if (isInvalidName(name)) {
            System.out.println("A name should not be empty.");
            return;
        }
        teams.putIfAbsent(name, new Team(name));
    }

    private static boolean isInvalidName(String name) {
        return name.isEmpty() || name.matches("\\s+") || name.trim().equals("");
    }
}
