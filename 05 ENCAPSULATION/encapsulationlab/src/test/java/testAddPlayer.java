import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class testAddPlayer {
    private static final String CLASS_NOT_PRESENT_ERROR_MESSAGE = "Class '%s' not present";
    private static final String METHOD_RETURN_TYPE_ERROR = "Method '%s' in class '%s' should have return type '%s'";
    private static final String TEAM_NAME = "TeamName";
    private static final String PERSON_CLASS_NAME = "Person";
    private static final String PERSON_METHOD_GET_AGE_NAME = "getAge";
    private static final String NAME_OF_FIELD_RESERVE_TEAM = "reserveTeam";
    private static final String NAME_OF_FIELD_FIRST_TEAM = "firstTeam";
    private static final int YOUNG_PERSON_AGE = 20;
    private static final int OLD_PERSON_AGE = 40;

    private static final String PERSON = "Team";


    private static final String[] methodNames = new String[]{
            "addPlayer"
    };

    private static final HashMap<String, Class> methodReturnTypes = new HashMap<>() {{
        put("addPlayer", void.class);
    }};

    private static final HashMap<String, Class[]> methodParameters = new HashMap<>() {{
        put("addPlayer", new Class[] {Object.class});
    }};
    private static final String CANNOT_ADD_PLAYER = "Can not add player";


    @Test
    public void test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        assertExistingMethodsAndWorksCorrect(methodNames);
    }

    private void assertExistingMethodsAndWorksCorrect(String[] methodNames) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        Class cl = getClass(PERSON);
        for (String methodName : methodNames) {
            Method method = cl.getDeclaredMethod(methodName, Classes.allClasses.get(PERSON_CLASS_NAME));
            Class<?> returnType = method.getReturnType();
            Assert.assertTrue(
                    String.format(METHOD_RETURN_TYPE_ERROR,
                            methodName,
                            PERSON,
                            methodReturnTypes.get(methodName)),
                    returnType.equals(methodReturnTypes.get(methodName)));

            Assert.assertTrue(CANNOT_ADD_PLAYER,
                    assertAddPlayerCorrect(method, cl));
        }
    }

    private boolean assertAddPlayerCorrect(Method method, Class cl) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return correctAddYoungPlayer(method, cl)
                && correctAddOldPlayer(method, cl);
    }

    private boolean correctAddOldPlayer(Method addPlayer, Class cl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor constructor = cl.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Object team = constructor.newInstance(TEAM_NAME);

        Object person = Mockito.mock(Classes.allClasses.get(PERSON_CLASS_NAME));
        Method getAge = person.getClass().getDeclaredMethod(PERSON_METHOD_GET_AGE_NAME);
        getAge.setAccessible(true);
        Mockito.when(getAge.invoke(person)).thenReturn(OLD_PERSON_AGE);

        addPlayer.invoke(team, person);

        Field reserveTeamField = team.getClass().getDeclaredField(NAME_OF_FIELD_RESERVE_TEAM);
        reserveTeamField.setAccessible(true);
        List<?> reserveTeam = (List<?>) reserveTeamField.get(team);

        return reserveTeam.size() > 0;
    }

    private boolean correctAddYoungPlayer(Method addPlayer, Class cl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        Constructor constructor = cl.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Object team = constructor.newInstance(TEAM_NAME);

        Object person = Mockito.mock(Classes.allClasses.get(PERSON_CLASS_NAME));
        Method getAge = person.getClass().getDeclaredMethod(PERSON_METHOD_GET_AGE_NAME);
        getAge.setAccessible(true);
        Mockito.when(getAge.invoke(person)).thenReturn(YOUNG_PERSON_AGE);

        addPlayer.invoke(team, person);

        Field firstTeamField = team.getClass().getDeclaredField(NAME_OF_FIELD_FIRST_TEAM);
        firstTeamField.setAccessible(true);
        List<?> firstTeam = (List<?>) firstTeamField.get(team);

        return firstTeam.size() > 0;
    }

    private Class getClass(String className) {
        Assert.assertTrue(String.format(CLASS_NOT_PRESENT_ERROR_MESSAGE, className),
                Classes.allClasses.containsKey(className));
        return Classes.allClasses.get(className);
    }
}
