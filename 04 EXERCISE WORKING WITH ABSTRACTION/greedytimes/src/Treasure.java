import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Treasure {
    private Map<String, Long> amounts;

    public Treasure() {
        this.amounts = new LinkedHashMap<>();
    }

    public void add(String name, long amount) {
        this.amounts.putIfAbsent(name, 0L);
        this.amounts.put(name, this.amounts.get(name) + amount);
    }

    public long getAmounts() {
        return this.amounts.values()
                .stream()
                .reduce((a, b) -> a + b)
                .orElse(0L);
    }

    public boolean isEmpty() {
        return this.amounts.isEmpty();
    }

    @Override
    public String toString() {
        return this.amounts.entrySet()
                .stream()
                .sorted((a, b) -> {
                    int compare = b.getKey().compareTo(a.getKey());
                    return compare != 0 ? compare : Long.compare(a.getValue(), b.getValue());
                })
                .map(entry -> String.format("##%s - %d", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining("\n"));
    }
}
