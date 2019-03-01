import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class testDoughCalculateCalories {
    private static final String CLASS_NOT_PRESENT_ERROR_MESSAGE = "Class '%s' not present";
    private static final String METHOD_RETURN_TYPE_ERROR = "Method '%s' in class '%s' should have return type '%s'";
    private static final String WRONG_RESULT = "Wrong result";
    private static final String TEST_METHOD_NAME = "calculateCalories";
    private static final String CLASS_NAME = "Dough";
    private static final String DOUGH_FLOUR_TYPE = "White";
    private static final String DOUGH_BAKING_TECHNIQUE = "Crispy";
    private static final double NORMAL_VALUE_OF_WEIGHT = 10D;

    private static final Map<String, Double> flourTypesAndModifiers =
            new HashMap<String, Double>() {{
                put("White", 1.5);
                put("Wholegrain", 1D);
            }};
    private static final Map<String, Double> bakingTypesAndModifiers =
            new HashMap<String, Double>() {{
                put("Crispy", 0.9);
                put("Chewy", 1.1);
                put("Homemade", 1D);
            }};

    private static final String[] methodNames = new String[]{
            TEST_METHOD_NAME
    };

    private static final HashMap<String, Class> methodReturnTypes = new HashMap<String, Class>() {{
        put(TEST_METHOD_NAME, double.class);
    }};

    private static final HashMap<String, Class[]> methodParameters = new HashMap<String, Class[]>() {{
        put(TEST_METHOD_NAME, new Class[]{});
    }};

    private static final HashMap<String, Class[]> constructorParameters =
            new HashMap<String, Class[]>() {{
                put(CLASS_NAME, new Class[]{String.class, String.class, double.class});
            }};

    private static final HashMap<String, Object[]> parametersForNewInstance =
            new HashMap<String, Object[]>() {{
                put(CLASS_NAME, new Object[]{DOUGH_FLOUR_TYPE, DOUGH_BAKING_TECHNIQUE, NORMAL_VALUE_OF_WEIGHT});
            }};

    @Test
    public void test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        assertExistingMethodsAndWorksCorrect(methodNames);
    }

    private void assertExistingMethodsAndWorksCorrect(String[] methodNames) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        Class cl = getClass(CLASS_NAME);
        for (String methodName : methodNames) {
            Method method =
                    methodParameters.get(methodName).length == 0
                            ? cl.getDeclaredMethod(methodName)
                            : cl.getDeclaredMethod(methodName, methodParameters.get(methodName));
            Class<?> returnType = method.getReturnType();
            Assert.assertTrue(
                    String.format(METHOD_RETURN_TYPE_ERROR,
                            methodName,
                            CLASS_NAME,
                            methodReturnTypes.get(methodName)),
                    returnType.equals(methodReturnTypes.get(methodName)));

            Assert.assertTrue(WRONG_RESULT,
                    assertMethodWorksCorrect(method, cl));
        }
    }

    private boolean assertMethodWorksCorrect(Method method, Class cl) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return testWithCorrectParameters(method, cl);
    }

    private boolean testWithCorrectParameters(Method method, Class cl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        for (String flourType : flourTypesAndModifiers.keySet()) {
            for (String bakingType : bakingTypesAndModifiers.keySet()) {
                Constructor constructor = cl.getDeclaredConstructor(
                        String.class, String.class, double.class);
                constructor.setAccessible(true);
                Object currentObject = constructor.newInstance(
                        flourType, bakingType, NORMAL_VALUE_OF_WEIGHT);

                method.setAccessible(true);
                double currentResult = (double) method.invoke(currentObject);
                double expectedCurrentResult = NORMAL_VALUE_OF_WEIGHT * 2
                        * flourTypesAndModifiers.get(flourType)
                        * bakingTypesAndModifiers.get(bakingType);
                if (expectedCurrentResult != currentResult) {
                    return false;
                }
            }
        }
        return true;
    }

    private Class getClass(String className) {
        Assert.assertTrue(String.format(CLASS_NOT_PRESENT_ERROR_MESSAGE, className),
                Classes.allClasses.containsKey(className));
        return Classes.allClasses.get(className);
    }
}
