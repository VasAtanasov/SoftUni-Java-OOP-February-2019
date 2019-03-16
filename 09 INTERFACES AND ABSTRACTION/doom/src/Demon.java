public class Demon extends Character<Integer> {
    private static String CHARACTER_TYPE = "Demon";

    public Demon(String username, int level, Double specialPoints) {
        super(username, CHARACTER_TYPE, level, specialPoints);
        super.setHashedPassword((super.getUsername().length() + 2) * 217);
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format("%.1f", super.getSpecialPoints().doubleValue() * super.getLevel());
    }
}
