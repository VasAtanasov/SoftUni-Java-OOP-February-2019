import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EnumSet;


public class Main {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    //    Problem 01. Card Suit
    //    Problem 02. Card Rank
    //    Problem 03. Cards with Power
    public static void main(String[] args) throws IOException {
        String input = reader.readLine();

        switch (input) {
            case "Card Suits":
                printSuit(input);
                break;
            case "Card Ranks":
                printRank(input);
                break;
            default:
                printCard(input);
                break;
        }

    }

    private static void printCard(String input) throws IOException {
        Rank rank = Enum.valueOf(Rank.class, input);
        Suit suit = Enum.valueOf(Suit.class, reader.readLine());
        Card card = new Card(suit, rank);
        System.out.println(card);
    }


    private static void printSuit(String input) {
        System.out.println(input + ":");
        EnumSet.allOf(Suit.class)
                .forEach(suit -> System.out.println(String.format("Ordinal value: %s; Name value: %s", suit.ordinal(), suit)));
    }

    private static void printRank(String input) {
        System.out.println(input + ":");
        EnumSet.allOf(Rank.class)
                .forEach(rank -> System.out.println(String.format("Ordinal value: %s; Name value: %s", rank.ordinal(), rank)));
    }

}
