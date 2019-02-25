import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class testPersonConstructorThreeParameters {
    private static final String CONSTRUCTOR_NOT_PRESENT_ERROR_MESSAGE = "Constructor %s not present";
    private static final String[] classNames = new String[] {
            "Person"
    };

    private static final HashMap<String, Class[]> constructorParameter = new HashMap<String, Class[]>() {{
        put("Person", new Class[] {String.class, String.class, int.class});
    }};

    @Test
    public void test() throws NoSuchMethodException {
        assertConstructors(classNames);
    }

    private void assertConstructors(String[] classNames) throws NoSuchMethodException {
        for (String className : classNames) {
            assertConstructorExists(className);
        }
    }

    private void assertConstructorExists(String className) throws NoSuchMethodException {
        Class cl = Classes.allClasses.get(className);

        Constructor constructor;

        try {
            constructor = cl.getDeclaredConstructor(constructorParameter.get(className));
        } catch (Exception e) {
            constructor = null;
        }
        Assert.assertNotNull(String.format(CONSTRUCTOR_NOT_PRESENT_ERROR_MESSAGE, className), constructor);
    }
}
