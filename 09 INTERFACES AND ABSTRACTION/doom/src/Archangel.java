public class Archangel extends Character {
    private static String CHARACTER_TYPE = "Demon";

    public Archangel(String username, int level, Integer specalPoints) {
        super(username, CHARACTER_TYPE, level, specalPoints);
        super.setHashedPassword(new StringBuilder(super.getUsername()).reverse().toString() + (super.getUsername().length() * 21));
    }

    @Override
    public String toString() {
        return super.toString() + (super.getSpecialPoints().doubleValue() * super.getLevel());
    }
}
