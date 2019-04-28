package cresla.factories;

import cresla.interfaces.Reactor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReactorFactory {
    private static final String REACTORS_PACKAGE = "cresla.entities.reactors.";
    private static final String REACTOR_POSTFIX = "Reactor";

    public static Reactor createReactor(String reactorType, int id, int additionalParameter, int moduleCapacity) {
        try {
            Class<?> reactorClass = Class.forName(REACTORS_PACKAGE + reactorType + REACTOR_POSTFIX);
            Constructor<?> declaredConstructor = reactorClass.getDeclaredConstructor(int.class, int.class, int.class);
            return (Reactor) declaredConstructor.newInstance(id, additionalParameter, moduleCapacity);
        } catch (ClassNotFoundException |
                InstantiationException |
                InvocationTargetException |
                NoSuchMethodException |
                IllegalAccessException e) {
            return null;
        }
    }
}
