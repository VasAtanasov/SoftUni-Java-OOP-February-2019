package cresla.factories;

import cresla.interfaces.Module;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ModuleFactory {
    private static final String REACTORS_PACKAGE = "cresla.entities.modules.";

    public static Module createModule(String moduleType, int id, int additionalParameter) {
        try {
            Class<?> moduleClass = Class.forName(REACTORS_PACKAGE + moduleType);
            Constructor<?> declaredConstructor = moduleClass.getDeclaredConstructor(int.class, int.class);
            return (Module) declaredConstructor.newInstance(id, additionalParameter);
        } catch (ClassNotFoundException |
                InstantiationException |
                InvocationTargetException |
                NoSuchMethodException |
                IllegalAccessException e) {
            return null;
        }
    }
}
