import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class testAllMethodsExists {

    private static final String CLASS_NOT_PRESENT_ERROR_MESSAGE = "Class '%s' not present";
    private static final String METHOD_RETURN_TYPE_ERROR = "Method '%s' in class '%s' should have return type '%s'";
    private static final String CLASS_NAME = "Pizza";
    private static final String CLASS_NAME_2 = "Dough";
    private static final String CLASS_NAME_3 = "Topping";
    private static final String SEARCHED_METHOD_1 = "setName";
    private static final String SEARCHED_METHOD_2 = "setDough";
    private static final String SEARCHED_METHOD_3 = "setToppings";
    private static final String SEARCHED_METHOD_4 = "getOverallCalories";
    private static final String SEARCHED_METHOD_5 = "setWeight";
    private static final String SEARCHED_METHOD_6 = "setFlourType";
    private static final String SEARCHED_METHOD_7 = "setBakingTechnique";
    private static final String SEARCHED_METHOD_8 = "calculateCalories";
    private static final String SEARCHED_METHOD_9 = "setToppingType";

    private static final String[] classNames = new String[]{
            CLASS_NAME,
            CLASS_NAME_2,
            CLASS_NAME_3
    };


    private static final Map<String, String[]> methodsInClass =
            new HashMap<String, String[]>() {{
                put(CLASS_NAME, new String[]{
                        SEARCHED_METHOD_1,
                        SEARCHED_METHOD_2,
                        SEARCHED_METHOD_3,
                        SEARCHED_METHOD_4
                });
                put(CLASS_NAME_2, new String[]{
                        SEARCHED_METHOD_5,
                        SEARCHED_METHOD_6,
                        SEARCHED_METHOD_7,
                        SEARCHED_METHOD_8
                });
                put(CLASS_NAME_3, new String[]{
                        SEARCHED_METHOD_5,
                        SEARCHED_METHOD_8,
                        SEARCHED_METHOD_9
                });
            }};

    private static final HashMap<String, Class> methodReturnTypes = new HashMap<String, Class>() {{
        put(SEARCHED_METHOD_1, void.class);
        put(SEARCHED_METHOD_2, void.class);
        put(SEARCHED_METHOD_3, void.class);
        put(SEARCHED_METHOD_4, double.class);
        put(SEARCHED_METHOD_5, void.class);
        put(SEARCHED_METHOD_6, void.class);
        put(SEARCHED_METHOD_7, void.class);
        put(SEARCHED_METHOD_8, double.class);
        put(SEARCHED_METHOD_9, void.class);

    }};

    private static final HashMap<String, Class[]> methodParameters = new HashMap<String, Class[]>() {{
        put(SEARCHED_METHOD_1, new Class[]{String.class});
        put(SEARCHED_METHOD_2, new Class[]{Classes.allClasses.get(CLASS_NAME_2)});
        put(SEARCHED_METHOD_3, new Class[]{int.class});
        put(SEARCHED_METHOD_4, new Class[]{});
        put(SEARCHED_METHOD_5, new Class[]{double.class});
        put(SEARCHED_METHOD_6, new Class[]{String.class});
        put(SEARCHED_METHOD_7, new Class[]{String.class});
        put(SEARCHED_METHOD_8, new Class[]{});
        put(SEARCHED_METHOD_9, new Class[]{String.class});

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


