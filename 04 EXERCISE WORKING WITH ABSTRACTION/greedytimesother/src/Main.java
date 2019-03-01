import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    private static final String REGEX = "(gold|[a-zA-Z]{3}|[a-zA-Z]+gem)[\\s]+([0-9]+)";
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        long capacity = Long.parseLong(reader.readLine());
        Map<String, Map<String, List<Long>>> bag = new LinkedHashMap<>();
        long gold = 0, gems = 0, cash = 0;

        String input = reader.readLine();
        Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String item = matcher.group(1);
            long amount = Long.parseLong(matcher.group(2));
            if (amount < 0) {
                continue;
            }

            if (capacity >= amount) {
                if (item.toLowerCase().endsWith("gem") && item.length() > 3) {
                    if (gems + amount <= gold) {
                        gems += amount;
                        bag.putIfAbsent("Gem", new TreeMap<>(Comparator.naturalOrder()));
                        bag.get("Gem").putIfAbsent(item, new ArrayList<>());
                        bag.get("Gem").get(item).add(amount);
                        capacity -= amount;
                    }
                } else if (item.length() == 3) {
                    if (cash + amount <= gems) {
                        cash += amount;
                        bag.putIfAbsent("Cash", new TreeMap<>(Comparator.naturalOrder()));
                        bag.get("Cash").putIfAbsent(item, new ArrayList<>());
                        bag.get("Cash").get(item).add(amount);
                        capacity -= amount;
                    }
                } else if (item.toLowerCase().endsWith("gold")) {
                    gold += amount;
                    bag.putIfAbsent("Gold", new TreeMap<>(Comparator.naturalOrder()));
                    bag.get("Gold").putIfAbsent(item, new ArrayList<>());
                    bag.get("Gold").get(item).add(amount);
                    capacity -= amount;
                }
            }
        }

        for (Map.Entry<String, Map<String, List<Long>>> entry : getSortedEntries(bag)) {
            System.out.println(String.format("<%s> $%d", entry.getKey(), getSum(entry.getValue())));
            for (Map.Entry<String, List<Long>> stringListEntry : sortEntries(entry.getValue().entrySet())) {
                System.out.println(String.format("##%s - %d", stringListEntry.getKey(), sumList(stringListEntry.getValue())));
            }
        }
    }

    private static Set<Map.Entry<String, List<Long>>> sortEntries(Set<Map.Entry<String, List<Long>>> entry) {
        return entry.stream()
                .sorted((a, b) -> {
                    int compare = b.getKey().compareTo(a.getKey());
                    return compare != 0 ? compare : Long.compare(sumList(a.getValue()), sumList(b.getValue()));
                })
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static Set<Map.Entry<String, Map<String, List<Long>>>> getSortedEntries(Map<String, Map<String, List<Long>>> bag) {
        return bag.entrySet().stream()
                .sorted((a, b) -> Long.compare(getSum(b.getValue()), getSum(a.getValue())))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static long getSum(Map<String, List<Long>> values) {
        return values
                .values()
                .stream()
                .map(Main::sumList)
                .reduce((a, b) -> a + b)
                .orElse(0L);
    }

    private static long sumList(List<Long> list) {
        return list
                .stream()
                .reduce((a, b) -> a + b)
                .orElse(0L);
    }


}
