import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Gandalf {
    private static Map<String, Supplier<Food>> foods = new HashMap<>() {{
        put("cram", Cram::new);
        put("lembas", Lembas::new);
        put("apple", Apple::new);
        put("melon", Melon::new);
        put("honeycake", HoneyCake::new);
        put("mushrooms", Mushrooms::new);
    }};

    private int foodPoints;

    public Gandalf() {
        this.foodPoints = 0;
    }

    public void eatFood(String food) {
        if (foods.containsKey(food.toLowerCase())) {
            this.foodPoints += foods.get(food.toLowerCase()).get().getPoints();
            return;
        }
        this.foodPoints += - 1;
    }

    public int getFoodPoints() {
        return this.foodPoints;
    }

    public String getMood() {
        if (this.foodPoints < - 5) {
            return "Angry";
        } else if (this.foodPoints < 0) {
            return "Sad";
        } else if (this.foodPoints <= 15) {
            return "Happy";
        }
        return "JavaScript";
    }


    @Override
    public String toString() {
        return String.format("%d\n%s", this.foodPoints, this.getMood());
    }
}
