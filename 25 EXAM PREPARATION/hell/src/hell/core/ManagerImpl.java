package hell.core;

import hell.entities.items.CommonItem;
import hell.entities.items.RecipeItem;
import hell.factories.HeroFactory;
import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Manager;
import hell.interfaces.Recipe;
import hell.repositories.HeroRepository;
import hell.repositories.Repository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
public class ManagerImpl implements Manager {
    private static final String CREATED_HERO = "Created %s - %s";
    private static final String CREATED_ITEM = "Added item - %s to Hero - %s";
    private static final String CREATED_RECIPE = "Added recipe - %s to Hero - %s";

    private static final Comparator<Hero> HERO_COMPARATOR = (a, b) -> {
        long thisHero = a.getStrength() + a.getAgility() + a.getIntelligence();
        long otherHero = b.getStrength() + b.getAgility() + b.getIntelligence();
        int cmp = Long.compare(otherHero, thisHero);
        return cmp != 0 ? cmp : Long.compare((b.getHitPoints() + b.getDamage()), (a.getHitPoints() + a.getDamage()));
    };

    private Repository<Hero> heroRepository;

    public ManagerImpl() {
        this.heroRepository = new HeroRepository();
    }

    @Override
    public String addHero(List<String> arguments) {
        String name = arguments.get(0);
        String heroType = arguments.get(1);
        Hero instance = HeroFactory.createHero(heroType, name);
        this.heroRepository.persist(instance);
        return String.format(CREATED_HERO, heroType, name);
    }

    @Override
    public String addItem(List<String> data) {
        String name = data.get(0);
        String heroName = data.get(1);
        int strengthBonus = Integer.parseInt(data.get(2));
        int agilityBonus = Integer.parseInt(data.get(3));
        int intelligenceBonus = Integer.parseInt(data.get(4));
        int hitPointsBonus = Integer.parseInt(data.get(5));
        int damageBonus = Integer.parseInt(data.get(6));
        Item item = new CommonItem(name, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus);
        Hero hero = this.heroRepository.getByName(heroName);
        hero.addItem(item);
        return String.format(CREATED_ITEM, name, heroName);
    }

    @Override
    public String addRecipe(List<String> data) {
        String name = data.get(0);
        String heroName = data.get(1);
        int strengthBonus = Integer.parseInt(data.get(2));
        int agilityBonus = Integer.parseInt(data.get(3));
        int intelligenceBonus = Integer.parseInt(data.get(4));
        int hitPointsBonus = Integer.parseInt(data.get(5));
        int damageBonus = Integer.parseInt(data.get(6));
        List<String> items = data.subList(7, data.size());
        Recipe recipe = new RecipeItem(name, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus, items);
        Hero hero = this.heroRepository.getByName(heroName);
        hero.addRecipe(recipe);
        return String.format(CREATED_RECIPE, name, heroName);
    }

    @Override
    public String inspect(List<String> data) throws NoSuchFieldException, IllegalAccessException {
        String heroName = data.get(0);
        Hero hero = this.heroRepository.getByName(heroName);
        StringBuilder sb = new StringBuilder(String.format("Hero: %s, Class: %s\n", hero.getName(), hero.getClass().getSimpleName()) +
                String.format("HitPoints: %d, Damage: %d\n", hero.getHitPoints(), hero.getDamage()) +
                String.format("Strength: %d\n", hero.getStrength()) +
                String.format("Agility: %d\n", hero.getAgility()) +
                String.format("Intelligence: %d\n", hero.getIntelligence())
        );

        Collection<Item> items = hero.getItems();

        if (items.isEmpty()) {
            sb.append("Items: None");
            return sb.toString();
        }

        for (Item item : items) {
            sb.append(String.format("###Item: %s\n", item.getName()))
                    .append(String.format("###+%d Strength\n", item.getStrengthBonus()))
                    .append(String.format("###+%d Agility\n", item.getAgilityBonus()))
                    .append(String.format("###+%d Intelligence\n", item.getIntelligenceBonus()))
                    .append(String.format("###+%d HitPoints\n", item.getHitPointsBonus()))
                    .append(String.format("###+%d Damage", item.getDamageBonus()));
        }
        return sb.toString();
    }

    @Override
    public String quit() throws NoSuchFieldException, IllegalAccessException {
        List<Hero> heroes = this.heroRepository.getAll().stream()
                .sorted(HERO_COMPARATOR)
                .collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();

        int index = 1;
        for (Hero hero : heroes) {
            sb.append(String.format("%d. %s: %s\n", index++, hero.getClass().getSimpleName(), hero.getName()));
            sb.append(String.format("###HitPoints: %d\n", hero.getHitPoints()));
            sb.append(String.format("###Damage: %d\n", hero.getDamage()));
            sb.append(String.format("###Strength: %d\n", hero.getStrength()));
            sb.append(String.format("###Agility: %d\n", hero.getAgility()));
            sb.append(String.format("###Intelligence: %d\n", hero.getIntelligence()));

            Collection<Item> items = hero.getItems();

            if (items.isEmpty()) {
                sb.append("###Items: None\n");
            } else {
                sb.append(String.format("###Items: %s\n", items.stream().map(Item::getName).collect(Collectors.joining(", "))));
            }
        }
        return sb.toString().trim();
    }
}
