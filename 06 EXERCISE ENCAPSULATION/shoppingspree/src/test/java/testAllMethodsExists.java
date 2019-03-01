import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class testAllMethodsExists {

    private static final String CLASS_NOT_PRESENT_ERROR_MESSAGE = "Class '%s' not present";
    private static final String METHOD_RETURN_TYPE_ERROR = "Method '%s' in class '%s' should have return type '%s'";
    private static final String CLASS_NAME = "Person";
    private static final String CLASS_NAME_2 = "Product";
    private static final String SEARCHED_METHOD_1 = "setName";
    private static final String SEARCHED_METHOD_2 = "setMoney";
    private static final String SEARCHED_METHOD_3 = "buyProduct";
    private static final String SEARCHED_METHOD_5 = "setCost";
    private static final String SEARCHED_METHOD_6 = "getCost";
    private static final String SEARCHED_METHOD_7 = "getName";

    private static final String[] classNames = new String[]{
            CLASS_NAME,
            CLASS_NAME_2
    };


    private static final Map<String, String[]> methodsInClass =
            new HashMap<>() {{
                put(CLASS_NAME, new String[] {
                        SEARCHED_METHOD_1,
                        SEARCHED_METHOD_2,
                        SEARCHED_METHOD_3,
                });
                put(CLASS_NAME_2, new String[] {
                        SEARCHED_METHOD_1,
                        SEARCHED_METHOD_5,
                        SEARCHED_METHOD_6,
                        SEARCHED_METHOD_7,
                });
            }};

    private static final HashMap<String, Class> methodReturnTypes = new HashMap<>() {{
        put(SEARCHED_METHOD_1, void.class);
        put(SEARCHED_METHOD_2, void.class);
        put(SEARCHED_METHOD_3, void.class);
        put(SEARCHED_METHOD_5, void.class);
        put(SEARCHED_METHOD_6, double.class);
        put(SEARCHED_METHOD_7, String.class);

    }};

    private static final HashMap<String, Class[]> methodParameters = new HashMap<>() {{
        put(SEARCHED_METHOD_1, new Class[] {String.class});
        put(SEARCHED_METHOD_2, new Class[] {double.class});
        put(SEARCHED_METHOD_3, new Class[] {Classes.allClasses.get(CLASS_NAME_2)});
        put(SEARCHED_METHOD_5, new Class[] {double.class});
        put(SEARCHED_METHOD_6, new Class[] {});
        put(SEARCHED_METHOD_7, new Class[] {});

    }};

    @Test
    public void test() throws NoSuchMethodException {
        assertExistingMethods(classNames);
    }

    private void assertExistingMethods(String[] classNames) throws NoSuchMethodException {
        for (String className : classNames) {

            Class cl = getClass(className);
            for (String methodName : methodsInClass.get(className)) {
                Method method =
                        methodParameters.get(methodName).length == 0
                                ? cl.getDeclaredMethod(methodName)
                                : cl.getDeclaredMethod(methodName, methodParameters.get(methodName));
                Class<?> returnType = method.getReturnType();
                Assert.assertTrue(
                        String.format(METHOD_RETURN_TYPE_ERROR,
                                methodName,
                                className,
                                methodReturnTypes.get(methodName)),
                        returnType.equals(methodReturnTypes.get(methodName)));
            }

        }
    }

    private Class getClass(String className) {
        Assert.assertTrue(String.format(CLASS_NOT_PRESENT_ERROR_MESSAGE, className),
                Classes.allClasses.containsKey(className));
        return Classes.allClasses.get(className);
    }
}


