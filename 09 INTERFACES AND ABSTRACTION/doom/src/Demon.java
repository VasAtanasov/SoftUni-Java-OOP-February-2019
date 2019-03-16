public class Demon extends Character {
    private static String CHARACTER_TYPE = "Archangel";

    public Demon(String username, int level, Double specalPoints) {
        super(username, CHARACTER_TYPE, level, specalPoints);
        super.setHashedPassword("" + super.getUsername().length() * 217);
    }

    @Override
    public String toString() {
        return super.toString() +  (super.getSpecialPoints().intValue() * super.getLevel());
    }
}
