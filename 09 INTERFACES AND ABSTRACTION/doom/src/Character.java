public abstract class Character<T> implements GameObject<T> {
    private String username;
    private String characterType;
    private int level;
    private Number specialPoints;
    private T hashedPassword;

    public Character(String username, String characterType, int level, Number specialPoints) {
        this.username = username;
        this.characterType = characterType;
        this.level = level;
        this.specialPoints = specialPoints;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getCharacterType() {
        return this.characterType;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public Number getSpecialPoints() {
        return this.specialPoints;
    }

    @Override
    public T getHashedPassword() {
        return this.hashedPassword;
    }

    @Override
    public void setHashedPassword(T hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    @Override
    public String toString() {
        return String.format("\"%s\" | \"%s\" -> %s%n",
                this.getUsername(),
                this.getHashedPassword(),
                this.getClass().getSimpleName());
    }

}
