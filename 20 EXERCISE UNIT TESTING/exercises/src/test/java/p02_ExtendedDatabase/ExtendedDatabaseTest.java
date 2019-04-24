package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ExtendedDatabaseTest {
    private static final Person[] PEOPLE = new Person[] {
            new Person(1, "Pesho"),
            new Person(2, "Gosho"),
            new Person(3, "Ivan"),
            new Person(4, "Stamat"),
            new Person(5, "Qnko"),
    };

    private Database database;

    @Before
    public void init() throws OperationNotSupportedException {
        this.database = new Database(PEOPLE);
    }

    @Test
    public void add_shouldWorkCorrect() throws OperationNotSupportedException {
        this.database.add(new Person(1, "Traicho"));
    }

    @Test
    public void findById_shouldReturnUser() throws OperationNotSupportedException {
        Person person = this.database.findById(1);
        Assert.assertEquals(PEOPLE[0], person);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findById_shouldThrowOnNonExistentUserId() throws OperationNotSupportedException {
        Person person = this.database.findById(10);
    }

    @Test
    public void findByUsername_shouldReturnUser() throws OperationNotSupportedException {
        Person person = this.database.findByUsername("Pesho");
        Assert.assertEquals(PEOPLE[0], person);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void findById_shouldThrowOnNonExistentUsername() throws OperationNotSupportedException {
        Person person = this.database.findByUsername("Traicho");
    }

    @Test
    public void remove_shouldRemoveLastAddedUser() throws OperationNotSupportedException {
        Person last = PEOPLE[PEOPLE.length - 1];

        this.database.remove();

        Person[] people = this.database.getElements();

        Assert.assertNotEquals(last, people[people.length - 1]);
    }
}
