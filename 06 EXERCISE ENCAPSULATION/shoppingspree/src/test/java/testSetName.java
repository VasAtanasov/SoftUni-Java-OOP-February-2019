import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class testSetName {
    private static final String CLASS_NOT_PRESENT_ERROR_MESSAGE = "Class '%s' not present";
    private static final String METHOD_RETURN_TYPE_ERROR = "Method '%s' in class '%s' should have return type '%s'";
    private static final String WRONG_FIELD_IS_SET = "Wrong result";
    private static final String NAME = "Name";
    private static final double MONEY = 100d;
    private static final String NEW_NAME = "NewName";
    private static final String NAME_OF_TESTED_FIELD = "name";
    private static final String TEST_METHOD_NAME = "setName";
    private static final String CLASS_NAME = "Person";
    private static final String CLASS_NAME_2 = "Product";
    private static final String WHITE_SPACES = "   ";

    private static final String[] classNames = new String[]{
            CLASS_NAME,
            CLASS_NAME_2
    };

    private static final Map<String, String[]> classesAndMethods =
            new HashMap<>() {{
                put(CLASS_NAME, new String[] {TEST_METHOD_NAME});
                put(CLASS_NAME_2, new String[] {TEST_METHOD_NAME});
            }};

    private static final HashMap<String, Class> methodReturnTypes = new HashMap<>() {{
        put(TEST_METHOD_NAME, void.class);
    }};

    private static final HashMap<String, Class[]> methodParameters = new HashMap<>() {{
        put(TEST_METHOD_NAME, new Class[] {String.class});
    }};

    private static final HashMap<String, String> testedFieldInClass =
            new HashMap<>() {{
                put(CLASS_NAME, NAME_OF_TESTED_FIELD);
                put(CLASS_NAME_2, TEST_METHOD_NAME);
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

            Assert.assertTrue(WRONG_FIELD_IS_SET,
                    assertMethodWorksCorrect(method, cl));
        }
    }

    private boolean assertMethodWorksCorrect(Method method, Class cl) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return correctSet(method, cl)
                && correctBehaviorOnSetWhiteSpaces(method, cl);
    }

    private boolean correctBehaviorOnSetWhiteSpaces(Method method, Class cl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor constructor = cl.getDeclaredConstructor(
                String.class, double.class);
        constructor.setAccessible(true);
        Object currentObject = constructor.newInstance(
                NAME, MONEY);

        method.setAccessible(true);
        boolean error = false;
        try {
            method.invoke(currentObject, WHITE_SPACES);
        } catch (InvocationTargetException ite) {
            error = true;
        }

        return error;
    }

    private boolean correctSet(Method method, Class cl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor constructor = cl.getDeclaredConstructor(
                String.class, double.class);
        constructor.setAccessible(true);
        Object currentObject = constructor.newInstance(
                NAME, MONEY);

        method.setAccessible(true);
        method.invoke(currentObject, NEW_NAME);

        Field field = currentObject.getClass()
                .getDeclaredField(NAME_OF_TESTED_FIELD);
        field.setAccessible(true);
        String result = (String) field.get(currentObject);

        return NEW_NAME.equals(result);
    }

    private Class getClass(String className) {
        Assert.assertTrue(String.format(CLASS_NOT_PRESENT_ERROR_MESSAGE, className),
                Classes.allClasses.containsKey(className));
        return Classes.allClasses.get(className);
    }
}


