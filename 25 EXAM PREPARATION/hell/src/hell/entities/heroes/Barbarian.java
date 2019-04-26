package hell.entities.heroes;

public class Barbarian extends AbstractHero {
    private static final int STRENGTH = 90;
    private static final int AGILITY = 25;
    private static final int INTELLIGENCE = 10;
    private static final int HITPOINTS = 350;
    private static final int DAMAGE = 150;

    public Barbarian(String name) {
        super(name, STRENGTH, AGILITY, INTELLIGENCE, HITPOINTS, DAMAGE);
    }
}
