import java.util.HashMap;
import java.util.Map;

public class Topping {
    private static final int CALORIES_PER_GRAM = 2;
    private static final String INVALID_TYPE = "Cannot place %s on top of your pizza.";
    private static final String INVALID_WEIGHT = "%s weight should be in the range [1..50].";

    private static final Map<String, Double> toppingParams = new HashMap<>() {{
        put("Meat", 1.2);
        put("Veggies", 0.8);
        put("Cheese", 1.1);
        put("Sauce", 0.9);
    }};

    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        if (! toppingParams.containsKey(toppingType)) {
            throw new IllegalArgumentException(String.format(INVALID_TYPE, toppingType));
        }
        this.toppingType = toppingType;
    }

    private void setWeight(double weight) {
        if (weight <= 0 || weight > 50) {
            throw new IllegalArgumentException(String.format(INVALID_WEIGHT, this.toppingType));
        }
        this.weight = weight;
    }

    public double calculateCalories() {
        return (CALORIES_PER_GRAM * this.weight) * toppingParams.get(this.toppingType);
    }
}
