import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AxeTests {
    private static final int AXE_INITIAL_ATTACK = 10;
    private static final int AXE_INITIAL_DURABILITY = 10;
    private static final int DUMMY_INITIAL_HEALTH = 10;
    private static final int DUMMY_EXPERIENCE = 10;

    private Axe axe;
    private Dummy dummy;

    @Before
    public void initializeDummyAndAxe() {
        this.axe = new Axe(AXE_INITIAL_ATTACK, AXE_INITIAL_DURABILITY);
        this.dummy = new Dummy(DUMMY_INITIAL_HEALTH, DUMMY_EXPERIENCE);
    }

    @Test
    public void weaponAttackLosesDurability() {
        this.axe.attack(dummy);
        Assert.assertEquals("Weapon isn't losing durability", 9, axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void brokenWeaponCantAttack() {
        this.axe.attack(dummy);
        this.axe.attack(dummy);
    }
}
