import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String REGEX = "(gold|[a-zA-Z]{3}|[a-zA-Z]+gem)[\\s]+([0-9]+)";

    private static BufferedReader reader;
    private static Bag bag;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        initBag();

        String input = reader.readLine();
        Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String item = matcher.group(1);
            long amount = Long.parseLong(matcher.group(2));
            bag.add(item,amount);
        }
        System.out.println(bag.toString());
    }

    private static void initBag() throws IOException {
        long capacity = Long.parseLong(reader.readLine());
        bag = new Bag(capacity);
    }
}
