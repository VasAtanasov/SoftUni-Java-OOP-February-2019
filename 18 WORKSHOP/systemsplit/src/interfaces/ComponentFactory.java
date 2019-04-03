package interfaces;

import models.hardwares.Hardware;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public interface ComponentFactory {

    @SuppressWarnings("unchecked")
    static <T extends Model> T create(String type, String name, long capacity, long memory) throws
            ClassNotFoundException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {

        Class<Hardware> commandClass = (Class<Hardware>) Class.forName(type);
        Constructor<Hardware> commandConstructor = commandClass.getDeclaredConstructor(String.class, long.class, long.class);
        commandConstructor.setAccessible(true);
        return (T) commandConstructor.newInstance(name, capacity, memory);
    }
}
