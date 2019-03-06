import java.util.LinkedHashMap;
import java.util.Map;

public class Team {
    private String name;
    private Map<String, Player> players;

    public Team(String name) {
        this.name = name;
        this.players = new LinkedHashMap<>();
    }

    public void add(String name, Player player) {
        this.players.putIfAbsent(name, player);
    }

    public boolean remove(String name) {
        if (this.players.containsKey(name)) {
            this.players.remove(name);
            return true;
        } else {
            return false;
        }
    }

    private double getTeamStats() {
        double totalStats = players.values()
                .stream()
                .map(Player::getAverageStats)
                .reduce(0.0, (a, b) -> a + b);
        if (players.size() > 0) {
            return totalStats / players.size();
        } else {
            return totalStats;
        }
    }

    @Override
    public String toString() {
        return String.format("%s - %s", this.name, Math.round(this.getTeamStats()));
    }
}
