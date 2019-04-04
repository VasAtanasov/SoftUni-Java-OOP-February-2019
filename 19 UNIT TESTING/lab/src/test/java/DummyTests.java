import org.junit.Assert;
import org.junit.Test;

public class DummyTests {
    private static final int ALIVE_DUMMY_INITIAL_HEALTH = 10;
    private static final int DEAD_DUMMY_INITIAL_HEALTH = 0;
    private static final int DUMMY_EXPERIENCE = 10;
    private static final int ATTACK_POINTS = 2;

    private Dummy dummy;

    @Test
    public void dummyLosesHealth() {
        this.dummy = new Dummy(ALIVE_DUMMY_INITIAL_HEALTH, DUMMY_EXPERIENCE);
        this.dummy.takeAttack(ATTACK_POINTS);
        Assert.assertEquals("Dummy isn't losing health", 8, dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void deadDummyThrowsException() {
        this.dummy = new Dummy(DEAD_DUMMY_INITIAL_HEALTH, DUMMY_EXPERIENCE);
        this.dummy.takeAttack(ATTACK_POINTS);
    }

    @Test
    public void deadDummyGivesExperience() {
        this.dummy = new Dummy(DEAD_DUMMY_INITIAL_HEALTH, DUMMY_EXPERIENCE);
        Assert.assertEquals("Dead dummy isn't giving experience", DUMMY_EXPERIENCE, dummy.giveExperience());
    }

    @Test(expected = IllegalStateException.class)
    public void aliveDummyCantGiveExperience() {
        this.dummy = new Dummy(ALIVE_DUMMY_INITIAL_HEALTH, DUMMY_EXPERIENCE);
        this.dummy.giveExperience();
    }
}
