import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class testSetWeight {

    private static final String CLASS_NOT_PRESENT_ERROR_MESSAGE = "Class '%s' not present";
    private static final String METHOD_RETURN_TYPE_ERROR = "Method '%s' in class '%s' should have return type '%s'";
    private static final String WRONG_RESULT_SET = "Wrong result";
    private static final String DOUGH_FLOUR_TYPE = "White";
    private static final String TOPPING_TYPE = "Meat";
    private static final String DOUGH_BAKING_TECHNIQUE = "Crispy";
    private static final double NORMAL_VALUE_OF_WEIGHT = 10D;
    private static final double NEGATIVE_WEIGHT = -10D;
    private static final double WEIGHT_VALUE_2 = 51D;
    private static final String NAME_OF_TESTED_FIELD = "weight";
    private static final String TEST_METHOD_NAME = "setWeight";
    private static final String CLASS_NAME = "Dough";
    private static final String CLASS_NAME_2 = "Topping";
    private static final String RIGHT_ERROR_MESSAGE_DOUGH = "Dough weight should be in the range [1..200].";
    private static final String RIGHT_ERROR_MESSAGE_TOPPING = "%s weight should be in the range [1..50].";


    private static final Map<String, String[]> classesAndMethods =
            new HashMap<String, String[]>() {{
                put(CLASS_NAME, new String[]{TEST_METHOD_NAME});
                put(CLASS_NAME_2, new String[]{TEST_METHOD_NAME});
            }};

    private static final HashMap<String, Class> methodReturnTypes = new HashMap<String, Class>() {{
        put(TEST_METHOD_NAME, void.class);
    }};

    private static final HashMap<String, Class[]> methodParameters = new HashMap<String, Class[]>() {{
        put(TEST_METHOD_NAME, new Class[]{double.class});
    }};

    private static final HashMap<String, Class[]> constructorParameters =
            new HashMap<String, Class[]>() {{
                put(CLASS_NAME, new Class[]{String.class, String.class, double.class});
                put(CLASS_NAME_2, new Class[]{String.class, double.class});
            }};

    private static final HashMap<String, Object[]> parametersForNewInstance =
            new HashMap<String, Object[]>() {{
                put(CLASS_NAME, new Object[]{DOUGH_FLOUR_TYPE, DOUGH_BAKING_TECHNIQUE, NORMAL_VALUE_OF_WEIGHT});
                put(CLASS_NAME_2, new Object[]{TOPPING_TYPE, NORMAL_VALUE_OF_WEIGHT});
            }};

    private static final HashMap<String, String> testedFieldInClass =
            new HashMap<String, String>() {{
                put(CLASS_NAME, NAME_OF_TESTED_FIELD);
                put(CLASS_NAME_2, NAME_OF_TESTED_FIELD);
            }};

    private static final HashMap<String, Double> parametersWithNewValues =
            new HashMap<String, Double>() {{
                put(CLASS_NAME, NEGATIVE_WEIGHT);
                put(CLASS_NAME_2, WEIGHT_VALUE_2);
            }};


    @Test
    public void test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        for (String className : classesAndMethods.keySet()) {
            assertExistingMethodsAndWorksCorrect(
                    classesAndMethods.get(className), className
            );
        }
    }

    private void assertExistingMethodsAndWorksCorrect(String[] methodNames, String className) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        Class cl = getClass(className);
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

            Assert.assertTrue(WRONG_RESULT_SET,
                    assertMethodWorksCorrect(method, cl));
        }
    }

    private boolean assertMethodWorksCorrect(Method method, Class cl) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return correctSet(method, cl)
                && throwCorrectException(method, cl);
    }

    private boolean throwCorrectException(Method method, Class cl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        String className = cl.getSimpleName();
        Constructor constructor = cl.getDeclaredConstructor(
                constructorParameters.get(className));
        constructor.setAccessible(true);
        Object currentObject = constructor.newInstance(
                parametersForNewInstance.get(className));

        method.setAccessible(true);
        boolean rightErrorMessage = false;
        try {
            method.invoke(currentObject, parametersWithNewValues.get(className));
        } catch (InvocationTargetException ite) {
            String searchedError = className.endsWith(CLASS_NAME)
                    ? RIGHT_ERROR_MESSAGE_DOUGH
                    : String.format(RIGHT_ERROR_MESSAGE_TOPPING, TOPPING_TYPE);
            if (searchedError.equals(String.valueOf(ite.getTargetException().getMessage()))) {
                rightErrorMessage = true;
            }
        }

        return rightErrorMessage;
    }

    private boolean correctSet(Method method, Class cl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        String className = cl.getSimpleName();
        Constructor constructor = cl.getDeclaredConstructor(
                constructorParameters.get(className));
        constructor.setAccessible(true);
        Object currentObject = constructor.newInstance(
                parametersForNewInstance.get(className));

        method.setAccessible(true);
        method.invoke(currentObject, NORMAL_VALUE_OF_WEIGHT);

        Field field = currentObject.getClass()
                .getDeclaredField(NAME_OF_TESTED_FIELD);
        field.setAccessible(true);

        double result = (double) field.get(currentObject);

        return NORMAL_VALUE_OF_WEIGHT == result;
    }

    private Class getClass(String className) {
        Assert.assertTrue(String.format(CLASS_NOT_PRESENT_ERROR_MESSAGE, className),
                Classes.allClasses.containsKey(className));
        return Classes.allClasses.get(className);
    }
}

