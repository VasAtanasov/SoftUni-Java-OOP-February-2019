public interface GameObject {
    String getUsername();

    String getCharacterType();

    Number getSpecialPoints();

    int getLevel();

    String getHashedPassword();

    void setHashedPassword(String password);
}
