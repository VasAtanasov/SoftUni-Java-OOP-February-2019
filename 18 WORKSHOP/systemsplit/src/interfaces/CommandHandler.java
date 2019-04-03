package interfaces;


import java.lang.reflect.InvocationTargetException;

public interface CommandHandler {
    void interpretCommand(String[] data) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

}
