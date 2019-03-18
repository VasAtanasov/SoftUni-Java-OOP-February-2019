import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

public class Main {
    private static BufferedReader reader;
    private static Map<String, Function<String[], Animal>> classes;
    private static List<Animal> animals;
    private static Map<String, Function<Integer, Food>> foods;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        classes = new HashMap<String, Function<String[], Animal>>() {{
            put("Cat", array -> new Cat(array[1], array[0], Double.parseDouble(array[2]), array[3], array[4]));
            put("Tiger", array -> new Tiger(array[1], array[0], Double.parseDouble(array[2]), array[3]));
            put("Zebra", array -> new Zebra(array[1], array[0], Double.parseDouble(array[2]), array[3]));
            put("Mouse", array -> new Mouse(array[1], array[0], Double.parseDouble(array[2]), array[3]));
        }};
        foods = new HashMap<String, Function<Integer, Food>>() {{
            put("Vegetable", Vegetable::new);
            put("Meat", Meat::new);
        }};
        animals = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (!"End".equals(input = reader.readLine())) {
            String[] animalTokens = input.split("\\s+");
            String[] foodTokens = reader.readLine().split("\\s+");

            String animalType = animalTokens[0];
            Animal animal = classes.get(animalType).apply(animalTokens);
            animals.add(animal);

            Food food = foods.get(foodTokens[0]).apply(Integer.parseInt(foodTokens[1]));

            System.out.println(animal.makeSound());
            try {
                animal.eat(food);
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }


        animals.forEach(System.out::println);
    }
}
