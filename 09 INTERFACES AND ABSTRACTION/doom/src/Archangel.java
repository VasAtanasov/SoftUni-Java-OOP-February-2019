public class Archangel extends Character<String> {
    private static String CHARACTER_TYPE = "Archangel";

    public Archangel(String username, int level, Integer specalPoints) {
        super(username, CHARACTER_TYPE, level, specalPoints);
        super.setHashedPassword(new StringBuilder(super.getUsername())
                .reverse()
                .append("\"")
                .append(((super.getUsername().length() + 2) * 21))
                .toString());
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format("%d",super.getSpecialPoints().intValue() * super.getLevel());
    }
}
