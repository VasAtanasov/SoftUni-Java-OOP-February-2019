import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class testBuyProduct {
    private static final String CLASS_NOT_PRESENT_ERROR_MESSAGE = "Class '%s' not present";
    private static final String METHOD_RETURN_TYPE_ERROR = "Method '%s' in class '%s' should have return type '%s'";
    private static final String WRONG_RESULT = "Wrong result";
    private static final String NAME = "PersonName";
    private static final double MONEY = 100d;
    private static final String TEST_METHOD_NAME = "buyProduct";
    private static final String CLASS_NAME = "Person";
    private static final String PRODUCT_CLASS_NAME = "Product";
    private static final String NAME_OF_METHOD_GET_COST = "getCost";
    private static final String NAME_OF_FIELD_PRODUCTS = "products";


    private static final String[] methodNames = new String[]{
            TEST_METHOD_NAME
    };

    private static final HashMap<String, Class> methodReturnTypes = new HashMap<>() {{
        put(TEST_METHOD_NAME, void.class);
    }};

    private static final HashMap<String, Class[]> methodParameters = new HashMap<>() {{
        put(TEST_METHOD_NAME, new Class[] {
                Classes.allClasses.get(PRODUCT_CLASS_NAME)});
    }};

    private Object currentObject;

    @Before
    public void createChickenAndFindMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class cl = getClass(CLASS_NAME);
        Constructor constructor = cl.getDeclaredConstructor(
                String.class, double.class);
        constructor.setAccessible(true);
        this.currentObject = constructor.newInstance(
                NAME, MONEY);
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
        return correctAdd(method, cl)
                && correctBehaviourWithExpensiveProduct(method, cl);
    }

    private boolean correctBehaviourWithExpensiveProduct(Method method, Class cl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        Class productClass = Classes.allClasses.get(PRODUCT_CLASS_NAME);
        Object product = Mockito.mock(productClass);
        Method getCost = productClass.getDeclaredMethod(NAME_OF_METHOD_GET_COST);
        getCost.setAccessible(true);
        Mockito.when(getCost.invoke(product)).thenReturn(MONEY + 1);

        method.setAccessible(true);
        boolean error = false;
        try {
            method.invoke(this.currentObject, product);
        } catch (InvocationTargetException ite) {
            error = true;
        }

        return error;
    }

    private boolean correctAdd(Method method, Class cl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        Class productClass = Classes.allClasses.get(PRODUCT_CLASS_NAME);
        Object product = Mockito.mock(productClass);
        Method getCost = productClass.getDeclaredMethod(NAME_OF_METHOD_GET_COST);
        getCost.setAccessible(true);
        Mockito.when(getCost.invoke(product)).thenReturn(MONEY - 1);

        method.setAccessible(true);
        method.invoke(this.currentObject, product);

        Field field = this.currentObject.getClass()
                .getDeclaredField(NAME_OF_FIELD_PRODUCTS);
        field.setAccessible(true);
        List list = (List) field.get(this.currentObject);

        return list.size() > 0;
    }

    private Class getClass(String className) {
        Assert.assertTrue(String.format(CLASS_NOT_PRESENT_ERROR_MESSAGE, className),
                Classes.allClasses.containsKey(className));
        return Classes.allClasses.get(className);
    }
}

