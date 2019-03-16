import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Main {
    private static BufferedReader reader;
    private static Map<String, Function<String[], Animal>> animals;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        animals = new HashMap<String, Function<String[], Animal>>() {{
            put("Cat", array -> new Cat(array[0], Integer.parseInt(array[1]), array[2]));
            put("Dog", array -> new Dog(array[0], Integer.parseInt(array[1]), array[2]));
            put("Frog", array -> new Frog(array[0], Integer.parseInt(array[1]), array[2]));
            put("Animal", array -> new Animal(array[0], Integer.parseInt(array[1]), array[2]));
            put("Kitten", array -> new Kitten(array[0], Integer.parseInt(array[1])));
            put("Tomcat", array -> new Tomcat(array[0], Integer.parseInt(array[1])));
        }};
    }

    public static void main(String[] args) throws IOException {
        try {
            String firstLine;
            while (! "Beast!".equals(firstLine = reader.readLine())) {
                String[] tokens = reader.readLine().split("\\s+");

                if (tokens.length < 2 || tokens.length > 3) {
                    throw new IllegalArgumentException("Invalid input!");
                }
                if (animals.containsKey(firstLine)) {
                    Animal animal = animals.get(firstLine).apply(tokens);
                    System.out.println(animal);
                } else {
                    System.out.println("Invalid input!");
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input!");
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }

    }
}
