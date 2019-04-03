import com.BlackBoxInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final String END_COMMAND = "END";

    private static Map<String, Method> methods;

    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        methods = init();
    }

    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor<BlackBoxInt> blackBoxIntConstructor = BlackBoxInt.class.getDeclaredConstructor(int.class);
        blackBoxIntConstructor.setAccessible(true);
        BlackBoxInt blackBoxInt = blackBoxIntConstructor.newInstance(0);

        String input;
        while (!END_COMMAND.equals(input = reader.readLine())) {
            String[] tokens = input.split("_");

            Method method = methods.get(tokens[0]);
            method.setAccessible(true);
            method.invoke(blackBoxInt, Integer.parseInt(tokens[1]));

            Field field = blackBoxInt.getClass().getDeclaredField("innerValue");
            field.setAccessible(true);
            System.out.println(field.get(blackBoxInt));
        }
    }

    private static Map<String, Method> init() {
        Class clazz = BlackBoxInt.class;

        Map<String, Method> methods = new HashMap<>();
        Arrays.stream(clazz.getDeclaredMethods()).forEach(method -> {
            method.setAccessible(true);
            methods.putIfAbsent(method.getName(), method);
        });

        return methods;
    }
}
