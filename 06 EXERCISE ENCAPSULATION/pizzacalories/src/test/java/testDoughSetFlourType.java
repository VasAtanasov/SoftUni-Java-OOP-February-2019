import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class testDoughSetFlourType {
    private static final String CLASS_NOT_PRESENT_ERROR_MESSAGE = "Class '%s' not present";
    private static final String METHOD_RETURN_TYPE_ERROR = "Method '%s' in class '%s' should have return type '%s'";
    private static final String WRONG_RESULT = "Wrong result";
    private static final String TEST_METHOD_NAME = "setFlourType";
    private static final String CLASS_NAME = "Dough";
    private static final String NAME_OF_FIELD = "flourType";
    private static final String DOUGH_FLOUR_TYPE = "White";
    private static final String ILLEGAL_FLOUR_TYPE = "IllegalFlourType";
    private static final String DOUGH_BAKING_TECHNIQUE = "Crispy";
    private static final double NORMAL_VALUE_OF_WEIGHT = 10D;
    private static final String RIGHT_ERROR_MESSAGE = "Invalid type of dough.";

    private static final String[] allPossibleFlourTypes = new String[]{
            "White", "Wholegrain"
    };

    private static final String[] methodNames = new String[]{
            TEST_METHOD_NAME
    };

    private static final HashMap<String, Class> methodReturnTypes = new HashMap<String, Class>() {{
        put(TEST_METHOD_NAME, void.class);
    }};

    private static final HashMap<String, Class[]> methodParameters = new HashMap<String, Class[]>() {{
        put(TEST_METHOD_NAME, new Class[]{String.class});
    }};

    private static final HashMap<String, Class[]> constructorParameters =
            new HashMap<String, Class[]>() {{
                put(CLASS_NAME, new Class[]{String.class, String.class, double.class});
            }};

    private static final HashMap<String, Object[]> parametersForNewInstance =
            new HashMap<String, Object[]>() {{
                put(CLASS_NAME, new Object[]{allPossibleFlourTypes[0], DOUGH_BAKING_TECHNIQUE, NORMAL_VALUE_OF_WEIGHT});
            }};

    private Object currentObject;

    @Before
    public void createChickenAndFindMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class cl = getClass(CLASS_NAME);
        Constructor constructor = cl.getDeclaredConstructor(
                constructorParameters.get(CLASS_NAME));
        constructor.setAccessible(true);
        this.currentObject = constructor.newInstance(
                parametersForNewInstance.get(CLASS_NAME)
        );
    }

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
        return testWithCorrectParameters(method, cl)
                && testWithIllegalArguments(method, cl);
    }

    private boolean testWithIllegalArguments(Method method, Class cl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        method.setAccessible(true);
        boolean rightErrorMessage = false;
        try {
            method.invoke(this.currentObject, ILLEGAL_FLOUR_TYPE);
        } catch (InvocationTargetException ite) {
            if (RIGHT_ERROR_MESSAGE.equals(String.valueOf(ite.getTargetException().getMessage()))) {
                rightErrorMessage = true;
            }
        }

        return rightErrorMessage;
    }

    private boolean testWithCorrectParameters(Method method, Class cl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        method.setAccessible(true);

        for (String possibleFlourType : allPossibleFlourTypes) {
            method.invoke(this.currentObject, possibleFlourType);
        }

        Field field = this.currentObject.getClass()
                .getDeclaredField(NAME_OF_FIELD);
        field.setAccessible(true);

        String result = (String) field.get(this.currentObject);

        return allPossibleFlourTypes[allPossibleFlourTypes.length - 1].equals(result);
    }

    private Class getClass(String className) {
        Assert.assertTrue(String.format(CLASS_NOT_PRESENT_ERROR_MESSAGE, className),
                Classes.allClasses.containsKey(className));
        return Classes.allClasses.get(className);
    }
}

