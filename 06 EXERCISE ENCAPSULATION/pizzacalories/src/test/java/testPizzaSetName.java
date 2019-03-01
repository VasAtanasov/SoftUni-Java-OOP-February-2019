import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class testPizzaSetName {

    private static final String CLASS_NOT_PRESENT_ERROR_MESSAGE = "Class '%s' not present";
    private static final String METHOD_RETURN_TYPE_ERROR = "Method '%s' in class '%s' should have return type '%s'";
    private static final String WRONG_RESULT = "Wrong result";
    private static final String TEST_METHOD_NAME = "setName";
    private static final String NAME_OF_TESTED_FIELD = "name";
    private static final String CLASS_NAME = "Pizza";
    private static final String NAME = "PizzaName";
    private static final String NEW_VALID_ARGUMENT = "NewPizzaName";
    private static final String ILLEGAL_ARGUMENT = "  ";
    private static final int NUMBER_OF_TOPPINGS = 5;
    private static final String RIGHT_ERROR_MESSAGE = "Pizza name should be between 1 and 15 symbols.";


    private static final String[] methodNames = new String[]{
            TEST_METHOD_NAME
    };

    private static final HashMap<String, Class> methodReturnTypes = new HashMap<String, Class>() {{
        put(TEST_METHOD_NAME, void.class);
    }};

    private static final HashMap<String, Class[]> methodParameters = new HashMap<String, Class[]>() {{
        put(TEST_METHOD_NAME, new Class[]{String.class});
    }};

    private Object currentObject;

    @Before
    public void createChickenAndFindMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class cl = getClass(CLASS_NAME);
        Constructor constructor = cl.getDeclaredConstructor(
                String.class, int.class);
        constructor.setAccessible(true);
        this.currentObject = constructor.newInstance(
                NAME, NUMBER_OF_TOPPINGS);
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
        return correctWork(method, cl)
                && throwCorrectException(method, cl);
    }

    private boolean throwCorrectException(Method method, Class cl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        method.setAccessible(true);
        try {
            method.invoke(this.currentObject, ILLEGAL_ARGUMENT);
        } catch (InvocationTargetException ite) {
            if (RIGHT_ERROR_MESSAGE.equals(String.valueOf(ite.getTargetException().getMessage()))) {
                return true;
            }
        }

        return false;
    }

    private boolean correctWork(Method method, Class cl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        method.setAccessible(true);
        method.invoke(this.currentObject, NEW_VALID_ARGUMENT);

        Field field = this.currentObject.getClass()
                .getDeclaredField(NAME_OF_TESTED_FIELD);
        field.setAccessible(true);
        String result = (String) field.get(this.currentObject);

        return NEW_VALID_ARGUMENT.equals(result);
    }

    private Class getClass(String className) {
        Assert.assertTrue(String.format(CLASS_NOT_PRESENT_ERROR_MESSAGE, className),
                Classes.allClasses.containsKey(className));
        return Classes.allClasses.get(className);
    }
}

