import java.util.Scanner;

public class RhombusOfStars {
    private static final String ASTERISK = "* ";
    private static final String SPACE = " ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int maxWidth = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i < maxWidth; i++) {
            printRow(maxWidth, i);
        }
        System.out.println(repeatString(ASTERISK, maxWidth));
        for (int i = maxWidth - 1; i > 0; i--) {
            printRow(maxWidth, i);
        }
    }

    private static void printRow(int maxWidth, int row) {
        System.out.print(repeatString(SPACE, maxWidth - row));
        System.out.print(repeatString(ASTERISK, row));
        System.out.println();
    }

    private static String repeatString(String sequence, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(sequence);
        }
        return sb.toString();
    }
}