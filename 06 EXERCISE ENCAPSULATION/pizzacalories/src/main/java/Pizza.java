import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;

public class Pizza {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final String INVALID_TOPPINGS_COUNT = "Number of toppings should be in range [0..10].";
    private static final String INVALID_NAME = "Pizza name should be between 1 and 15 symbols.";

    private String name;
    private Dough dough;
    private Topping[] toppings;
    private int toppingsCount;
    private int index;

    public Pizza(String name, int toppingsCount) {
        this.setName(name);
        this.setToppings(toppingsCount);
        this.toppingsCount = toppingsCount;
        this.index = 0;
    }

    public int getToppingsCount() {
        return this.toppingsCount;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        if (name.length() < 1 || name.length() >= 15) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.name = name;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    private void setToppings(int toppingsCount) {
        if (toppingsCount < 0 || toppingsCount > 10) {
            throw new IllegalArgumentException(INVALID_TOPPINGS_COUNT);
        }
        this.toppings = new Topping[toppingsCount];
    }

    public double getOverallCalories() {
        return this.calculateToppingsCalories() + this.dough.calculateCalories();
    }

    public void addTopping(Topping topping) {
        if (this.index >= this.toppings.length) {
            throw new IllegalArgumentException(INVALID_TOPPINGS_COUNT);
        }
        this.toppings[index++] = topping;
    }

    private double calculateToppingsCalories() {
        return Arrays.stream(this.toppings)
                .filter(Objects::nonNull)
                .map(Topping::calculateCalories)
                .reduce((a, b) -> a + b)
                .orElse(0.0);
    }

    @Override
    public String toString() {
        return this.name + " - " + df.format(this.getOverallCalories());
    }
}
