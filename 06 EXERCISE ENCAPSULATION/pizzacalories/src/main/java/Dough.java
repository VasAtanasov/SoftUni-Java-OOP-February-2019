import java.util.HashMap;
import java.util.Map;

public class Dough {
    private static final int CALORIES_PER_GRAM = 2;
    private static final String INVALID_DOUGH = "Invalid type of dough.";
    private static final String INVALID_WIGHT = "Dough weight should be in the range [1..200].";

    private static final Map<String, Double> doughParams = new HashMap<>() {{
        put("White", 1.5);
        put("Wholegrain", 1.0);
        put("Crispy", 0.9);
        put("Chewy", 1.1);
        put("Homemade", 1.0);
    }};

    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    private void setFlourType(String flourType) {
        if (! doughParams.containsKey(flourType)) {
            throw new IllegalArgumentException(INVALID_DOUGH);
        }
        this.flourType = flourType;
    }

    private void setBakingTechnique(String bakingTechnique) {
        if (! doughParams.containsKey(bakingTechnique)) {
            throw new IllegalArgumentException(INVALID_DOUGH);
        }
        this.bakingTechnique = bakingTechnique;
    }

    private void setWeight(double weight) {
        if (weight <= 0 || weight > 200) {
            throw new IllegalArgumentException(INVALID_WIGHT);
        }
        this.weight = weight;
    }

    public double calculateCalories() {
        return (CALORIES_PER_GRAM * this.weight) *
                doughParams.get(this.flourType) *
                doughParams.get(this.bakingTechnique);
    }
}
