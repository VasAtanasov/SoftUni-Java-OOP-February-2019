package hell.interfaces;

public interface CommandHandler {
    String handleCommand(String[] data) throws IllegalAccessException, NoSuchFieldException;
}
