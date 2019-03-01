import java.util.*;
import java.util.stream.Collectors;

public class Bag {
    private static final String GOLD = "gold";

    private static final String GEM = "gem";

    private enum TreasureType {Gold, Gem, Cash}

    private long capacity;
    private long totalInBag;
    private Map<TreasureType, Treasure> treasures;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.totalInBag = 0;

        this.treasures = new TreeMap<>(Comparator.naturalOrder()) {{
            put(TreasureType.Cash, new Treasure());
            put(TreasureType.Gold, new Treasure());
            put(TreasureType.Gem, new Treasure());
        }};
    }

    public void add(String item, long amount) {
        if (amount < 0 || (this.totalInBag + amount > this.capacity)) {
            return;
        }

        long gold = this.treasures.get(TreasureType.Gold).getAmounts();
        long gems = this.treasures.get(TreasureType.Gem).getAmounts();
        long cash = this.treasures.get(TreasureType.Cash).getAmounts();

        if (item.toLowerCase().endsWith(GEM) && item.length() > 3) {
            if (gems + amount <= gold) {
                this.treasures.get(TreasureType.Gem).add(item, amount);
                this.totalInBag += amount;
            }
        } else if (item.length() == 3) {
            if (cash + amount <= gems) {
                this.treasures.get(TreasureType.Cash).add(item, amount);
                this.totalInBag += amount;
            }
        } else if (item.toLowerCase().endsWith(GOLD)) {
            this.treasures.get(TreasureType.Gold).add(item, amount);
            this.totalInBag += amount;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<TreasureType, Treasure> entry : getSortedEntries()) {
            if (entry.getValue().isEmpty()) {
                continue;
            }
            sb.append(String.format("<%s> $%d", entry.getKey().toString(), entry.getValue().getAmounts()));
            sb.append(System.lineSeparator());
            sb.append(entry.getValue().toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    private Set<Map.Entry<TreasureType, Treasure>> getSortedEntries() {
        return treasures.entrySet()
                .stream()
                .sorted((a, b) -> Long.compare(b.getValue().getAmounts(), a.getValue().getAmounts()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
