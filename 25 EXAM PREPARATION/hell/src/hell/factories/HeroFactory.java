package hell.factories;

import hell.entities.heroes.Assassin;
import hell.entities.heroes.Barbarian;
import hell.entities.heroes.Wizard;
import hell.interfaces.Hero;

public class HeroFactory {

    private enum HeroType {
        Barbarian, Assassin, Wizard
    }

    public static Hero createHero(String heroType, String name) {
        HeroType type = HeroType.valueOf(heroType);
        switch (type) {
            case Barbarian:
                return new Barbarian(name);
            case Assassin:
                return new Assassin(name);
            case Wizard:
                return new Wizard(name);
        }
        return null;
    }
}
