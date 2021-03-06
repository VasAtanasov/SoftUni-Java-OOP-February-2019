import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class testFieldsPersonTeamClassesExists {
    private static final String CLASS_NOT_PRESENT_ERROR_MESSAGE = "Class '%s' not present";
    private static final String FIELD_IS_MISSING_ERROR_MESSAGE = "Field '%s' is missing";

    private static final String[] classNames = new String[]{"Person", "Team"};
    private static final Map<String, String[]> allNeededFields =
            new HashMap<>() {{
                put("Person", new String[] {"firstName", "lastName", "age", "salary"});
                put("Team", new String[] {"name", "firstTeam", "reserveTeam"});
            }};

    @Test
    public void test() {
        assertHaveAllFields(classNames);
    }

    private void assertHaveAllFields(String[] classNames) {
        for (String className : classNames) {
            haveAllFields(className);
        }
    }

    private void haveAllFields(String className) {
        Assert.assertTrue(String.format(CLASS_NOT_PRESENT_ERROR_MESSAGE, className),
                Classes.allClasses.containsKey(className));

        Class cl = Classes.allClasses.get(className);
        Field[] fields = cl.getDeclaredFields();

        for (String field : allNeededFields.get(className)) {
            Assert.assertTrue(String.format(FIELD_IS_MISSING_ERROR_MESSAGE, field),
                    Arrays.stream(fields)
                            .anyMatch(x -> x.getName().equalsIgnoreCase(field)));
        }
    }
}
