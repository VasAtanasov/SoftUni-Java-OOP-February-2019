import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class testToStringOverride {
    private static final String CLASS_NOT_PRESENT_ERROR_MESSAGE = "Class %s not present";

    private static final String[] classNames = new String[]{"Chicken"};

    private static final String[] methodNames = new String[]{"toString"};


    @Test
    public void test() throws NoSuchMethodException {
        assertExistingMethodsInClasses(classNames, methodNames);
    }

    private void assertExistingMethodsInClasses(String[] classNames, String[] methodNames) throws NoSuchMethodException {
        for (String className : classNames) {
            for (String methodName : methodNames) {
                assertMethodExist(className, methodName);
            }
        }
    }

    private void assertMethodExist(String className, String methodName) throws NoSuchMethodException {
        Assert.assertTrue(String.format(CLASS_NOT_PRESENT_ERROR_MESSAGE, className),
                Classes.allClasses.containsKey(className));

        Class cl = Classes.allClasses.get(className);
        Method toString = cl.getDeclaredMethod(methodName);
    }
}

