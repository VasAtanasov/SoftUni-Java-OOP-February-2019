import org.junit.Assert;
import org.junit.Test;

public class testPersonClassExists {

    private static final String CLASS_NOT_FOUND_ERROR_MESSAGE = "Class '%s' not present";

    private static final String[] classNames = new String[]{
            "Person"
    };

    @Test
    public void test() throws ClassNotFoundException {
        assertExistingClasses(classNames);
    }

    private void assertExistingClasses(String[] classNames) throws ClassNotFoundException {
        for (String className : classNames) {
            assertExistingClass(className);
        }
    }

    private void assertExistingClass(String className) throws ClassNotFoundException {

        Assert.assertTrue(String.format(CLASS_NOT_FOUND_ERROR_MESSAGE, className),
                Classes.allClasses.containsKey(className));

    }
}