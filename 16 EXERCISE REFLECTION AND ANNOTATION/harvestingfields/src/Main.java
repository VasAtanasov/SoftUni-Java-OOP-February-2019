import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.function.Function;

public class Main {
    private static final String END_COMMAND = "HARVEST";
    private static final String PRIVATE = "private";
    private static final String PROTECTED = "protected";
    private static final String PUBLIC = "public";

    private static final Function<Field, String> fieldToString =
            field -> String.format("%s %s %s",
                    Modifier.toString(field.getModifiers()),
                    field.getType().getSimpleName(),
                    field.getName()
            );

    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        String input;
        Class<?> clazz = RichSoilLand.class;
        Field[] fields = clazz.getDeclaredFields();
        while (! END_COMMAND.equals(input = reader.readLine())) {
            switch (input) {
                case PRIVATE:
                    printFilteredFields(fields, PRIVATE);
                    break;
                case PUBLIC:
                    printFilteredFields(fields, PUBLIC);
                    break;
                case PROTECTED:
                    printFilteredFields(fields, PROTECTED);
                    break;
                default:
                    Arrays.stream(fields)
                            .map(fieldToString)
                            .forEach(System.out::println);
                    break;
            }
        }
    }

    private static void printFilteredFields(Field[] fields, String modifier) {
        Arrays.stream(fields)
                .filter(field -> Modifier.toString(field.getModifiers()).equals(modifier))
                .map(fieldToString)
                .forEach(System.out::println);
    }
}
