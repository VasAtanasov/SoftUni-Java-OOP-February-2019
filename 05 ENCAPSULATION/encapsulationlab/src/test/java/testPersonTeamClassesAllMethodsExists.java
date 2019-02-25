import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class testPersonTeamClassesAllMethodsExists {

    private static final String CLASS_NOT_PRESENT_ERROR_MESSAGE = "Class '%s' not present";
    private static final String METHOD_RETURN_TYPE_ERROR = "Method '%s' in class '%s' should have return type '%s'";

    private static final String[] classNames = new String[]{
            "Person", "Team"
    };


    private static final Map<String, String[]> methodsInClass =
            new HashMap<String, String[]>() {{
                put("Person", new String[]{
                        "setSalary",
                        "setFirstName",
                        "setLastName",
                        "setAge",
                });
                put("Team", new String[]{
                        "getFirstTeam",
                        "getReserveTeam"});
            }};

    private static final HashMap<String, Class> methodReturnTypes = new HashMap<String, Class>() {{
        put("setSalary", void.class);
        put("setFirstName", void.class);
        put("setLastName", void.class);
        put("setAge", void.class);
        put("getFirstTeam", List.class);
        put("getReserveTeam",  List.class);
    }};

    private static final HashMap<String, Class[]> methodParameters = new HashMap<String, Class[]>() {{
        put("setSalary", new Class[]{double.class});
        put("setFirstName", new Class[]{String.class});
        put("setLastName", new Class[]{String.class});
        put("setAge", new Class[]{int.class});
        put("getFirstTeam", new Class[]{});
        put("getReserveTeam",  new Class[]{});
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
