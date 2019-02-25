import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;

public class testPersonClassHaveAllMethods {
    private static final String CLASS_NOT_PRESENT_ERROR_MESSAGE = "Class '%s' not present";
    private static final String METHOD_RETURN_TYPE_ERROR = "Method '%s' in class '%s' should have return type '%s'";

    private static final String PERSON = "Person";


    private static final String[] methodNames = new String[]{
            "getSalary",
            "setSalary",
            "setFirstName",
            "setLastName",
            "setAge",
            "increaseSalary"
    };

    private static final HashMap<String, Class> methodReturnTypes = new HashMap<String, Class>() {{
        put("getSalary", double.class);
        put("setSalary", void.class);
        put("setFirstName", void.class);
        put("setLastName", void.class);
        put("setAge", void.class);
        put("increaseSalary", void.class);
    }};

    private static final HashMap<String, Class[]> methodParameters = new HashMap<String, Class[]>() {{
        put("getSalary", new Class[]{});
        put("setSalary", new Class[]{double.class});
        put("setFirstName", new Class[]{String.class});
        put("setLastName", new Class[]{String.class});
        put("setAge", new Class[]{int.class});
        put("increaseSalary", new Class[]{double.class});
    }};

    @Test
    public void test() throws NoSuchMethodException {
        assertExistingMethods(methodNames);
    }

    private void assertExistingMethods(String[] methodNames) throws NoSuchMethodException {
        Class cl = getClass(PERSON);
        for (String methodName : methodNames) {
            Method method =
                    methodParameters.get(methodName).length == 0
                            ? cl.getDeclaredMethod(methodName)
                            : cl.getDeclaredMethod(methodName, methodParameters.get(methodName));
            Class<?> returnType = method.getReturnType();
            Assert.assertTrue(
                    String.format(METHOD_RETURN_TYPE_ERROR,
                            methodName,
                            PERSON,
                            methodReturnTypes.get(methodName)),
                    returnType.equals(methodReturnTypes.get(methodName)));
        }
    }

    private Class getClass(String className) {
        Assert.assertTrue(String.format(CLASS_NOT_PRESENT_ERROR_MESSAGE, className),
                Classes.allClasses.containsKey(className));
        return Classes.allClasses.get(className);
    }
}


