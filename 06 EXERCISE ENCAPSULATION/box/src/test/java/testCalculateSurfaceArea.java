import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class testCalculateSurfaceArea {
    private static final String CLASS_NOT_PRESENT_ERROR_MESSAGE = "Class '%s' not present";
    private static final String METHOD_RETURN_TYPE_ERROR = "Method '%s' in class '%s' should have return type '%s'";
    private static final String WRONG_RESULT = "Wrong result from method %s";
    private static final double BOX_LENGTH = 1d;
    private static final double BOX_WIDTH = 2d;
    private static final double BOX_HEIGHT = 3d;

    private static final String BOX = "Box";


    private static final String[] methodNames = new String[]{
            "calculateSurfaceArea"
    };

    private static final HashMap<String, Class> methodReturnTypes = new HashMap<>() {{
        put("calculateSurfaceArea", double.class);
    }};

    private static final HashMap<String, Class[]> methodParameters = new HashMap<>() {{
        put("calculateSurfaceArea", new Class[] {});
    }};


    @Test
    public void test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        assertExistingMethodsAndWorksCorrect(methodNames);
    }

    private void assertExistingMethodsAndWorksCorrect(String[] methodNames) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        Class cl = getClass(BOX);
        for (String methodName : methodNames) {
            Method method =
                    methodParameters.get(methodName).length == 0
                            ? cl.getDeclaredMethod(methodName)
                            : cl.getDeclaredMethod(methodName,
                            methodParameters.get(methodName));
            Class<?> returnType = method.getReturnType();
            Assert.assertTrue(
                    String.format(METHOD_RETURN_TYPE_ERROR,
                            methodName,
                            BOX,
                            methodReturnTypes.get(methodName)),
                    returnType.equals(methodReturnTypes.get(methodName)));

            Assert.assertTrue(String.format(WRONG_RESULT, methodName),
                    assertCalculateSurfaceCorrect(method, cl));
        }
    }

    private boolean assertCalculateSurfaceCorrect(Method method, Class cl) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return correctCalculateSurface(method, cl);
    }


    private boolean correctCalculateSurface(Method calculateSurfaceArea, Class cl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        Constructor constructor = cl.getDeclaredConstructor(
                double.class, double.class, double.class);
        constructor.setAccessible(true);
        Object box = constructor
                .newInstance(BOX_LENGTH, BOX_WIDTH, BOX_HEIGHT);

        double result = (double) calculateSurfaceArea.invoke(box);

        double expectedResult = (2 * BOX_LENGTH * BOX_WIDTH) +
                (2 * BOX_LENGTH * BOX_HEIGHT) + (2 * BOX_WIDTH * BOX_HEIGHT);

        return expectedResult == result;
    }

    private Class getClass(String className) {
        Assert.assertTrue(String.format(CLASS_NOT_PRESENT_ERROR_MESSAGE, className),
                Classes.allClasses.containsKey(className));
        return Classes.allClasses.get(className);
    }
}