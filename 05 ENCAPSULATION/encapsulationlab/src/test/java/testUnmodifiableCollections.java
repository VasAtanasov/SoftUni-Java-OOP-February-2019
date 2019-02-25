import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class testUnmodifiableCollections {

    private static final String CLASS_NOT_PRESENT_ERROR = "Class '%s' not present";
    private static final String HAS_NON_PRIVATE_FIELDS_ERROR = "Class '%s' contains non private fields";


    private static final String[] classNames = new String[]{
            "Team"
    };

    private static final HashMap<String, Class[]> constructorParameters = new HashMap<String, Class[]>() {{
        put("Team", new Class[] {String.class});
    }};

    static final Map<Class<?>, Object> defaultValues = new HashMap<Class<?>, Object>() {{
        put(boolean.class, Boolean.FALSE);
        put(byte.class, (byte) 0);
        put(short.class, (short) 0);
        put(int.class, 0);
        put(long.class, 0L);
        put(char.class, '\0');
        put(float.class, 0.0F);
        put(double.class, 0.0);
        put(Boolean.class, Boolean.FALSE);
        put(Byte.class, (byte) 0);
        put(Short.class, (short) 0);
        put(Integer.class, 0);
        put(Long.class, 0L);
        put(Character.class, 0L);
        put(Float.class, 0L);
        put(Double.class, 0L);
    }};

    @Test
    public void test() throws Exception {
        assertPrivateFields(classNames);
        assertModifiableCollections(classNames);
    }

    private void assertModifiableCollections(String[] classNames) throws Exception {
        for (String className : classNames) {
            assertClassForModifiableCollection(className);
        }
    }

    private void assertClassForModifiableCollection(String className) throws Exception {
        Class cl = getClass(className);
        List<Method> nonPrivateMethods = Stream.of(cl.getMethods())
                .filter(m -> !Modifier.isPrivate(m.getModifiers()))
                .filter(m -> m.getParameterCount() == 0)
                .collect(Collectors.toList());

        for (Method nonPrivateMethod : nonPrivateMethods) {

            Class returnType = nonPrivateMethod.getReturnType();

            if (Iterable.class.isAssignableFrom(returnType)) {
                if (returnType.getSimpleName().equals(Iterable.class.getSimpleName())) {
                    continue; // iterable cannot be modified
                }

                Constructor c = cl.getConstructor(constructorParameters.get(className));
                Class<?>[] parameterTypes = constructorParameters.get(className);
                Object[] arguments = getArguments(parameterTypes);
                Object o = c.newInstance(arguments);
                Collection collection = (Collection) nonPrivateMethod.invoke(o);
                try {
                    collection.clear(); // ** should throw
                    throw new Exception("modifiable collection");
                } catch (UnsupportedOperationException uoe) {
                    // no problem
                }
            } else if (Map.class.isAssignableFrom(returnType)) {
                Constructor c = cl.getConstructor(constructorParameters.get(className));
                Class<?>[] parameterTypes = constructorParameters.get(className);
                Object[] arguments = getArguments(parameterTypes);
                Object o = c.newInstance(arguments);
                Map map = (Map) nonPrivateMethod.invoke(o);
                try {
                    map.clear(); // ** should throw
                    throw new Exception("Method returns a modifiable collection");
                } catch (UnsupportedOperationException uoe) {
                    // no problem
                }
            }
        }
    }

    private Object[] getArguments(Class<?>[] parameterTypes) {
        Object[] parameters = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            parameters[i] = defaultValues.get(parameterTypes[i]);
        }

        return parameters;
    }

    private void assertPrivateFields(String[] classNames) {
        for (String className : classNames) {
            assertHasNoPrivateFields(className);
        }
    }

    private Class getClass(String className) {
        Assert.assertTrue(String.format(CLASS_NOT_PRESENT_ERROR, className),
                Classes.allClasses.containsKey(className));
        return Classes.allClasses.get(className);
    }

    private void assertHasNoPrivateFields(String className) {
        Class cl = getClass(className);
        Field[] fields = cl.getDeclaredFields();
        long nonPrivateFieldsCount = Stream.of(fields).filter(f -> !Modifier.isPrivate(f.getModifiers())).count();
        Assert.assertTrue(
                String.format(HAS_NON_PRIVATE_FIELDS_ERROR, className),
                nonPrivateFieldsCount == 0);
    }
}
