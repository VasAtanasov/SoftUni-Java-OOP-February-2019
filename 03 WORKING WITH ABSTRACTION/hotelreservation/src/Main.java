import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        String[] tokens = reader.readLine().split("\\s+");
        double price = Double.parseDouble(tokens[0]);
        int days = Integer.parseInt(tokens[1]);
        SeasonType season = SeasonType.valueOf(tokens[2]);
        DiscountType discount = DiscountType.valueOf(tokens[3]);

        System.out.println(String.format("%.2f",
                PriceCalculator.calculatePrice(price, days, discount, season))
        );
    }
}
