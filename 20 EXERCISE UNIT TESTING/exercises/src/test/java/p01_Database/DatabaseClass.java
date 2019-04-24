package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class DatabaseClass {
    private static final Integer[] TEST_ARRAY = new Integer[] {1, 2, 3};
    private static final int WRONG_CAPACITY = 17;

    private Database database;

    @Before
    public void init() throws OperationNotSupportedException {
        this.database = new Database(TEST_ARRAY);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testWrongCapacityInitialization() throws OperationNotSupportedException {
        Integer[] array = new Integer[WRONG_CAPACITY];
        Arrays.fill(array, 1);
        this.database = new Database(array);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void addElement_shouldThrowExceptionOnNullElement() throws OperationNotSupportedException {
        this.database.add(null);
    }

    @Test
    public void addElement_shouldWorkCorrectWithValidElements() {
        Integer[] actual = this.database.getElements();
        Assert.assertArrayEquals(TEST_ARRAY, actual);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void remove_shouldThrowExceptionOnEmptyDatabase() throws OperationNotSupportedException {
        int length = this.database.getElements().length;
        for (int i = 0; i < length; i++) {
            this.database.remove();
        }
        this.database.remove();
    }

    @Test
    public void remove_shouldWorkCorrect() throws OperationNotSupportedException {
        this.database.remove();
        Assert.assertEquals(2, this.database.getElements().length);
    }

    @Test
    public void getElements_shouldReturnArray() {
       Assert.assertArrayEquals(TEST_ARRAY,this.database.getElements());
    }
}
