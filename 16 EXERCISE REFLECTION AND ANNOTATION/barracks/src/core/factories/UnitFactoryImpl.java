package core.factories;

import contracts.Unit;
import contracts.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class UnitFactoryImpl implements UnitFactory {

    private static final String UNITS_PACKAGE_NAME = "models.units.";

    @Override
    @SuppressWarnings("unchecked")
    public Unit createUnit(String unitType) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<Unit> unitClass = (Class<Unit>) Class.forName(UNITS_PACKAGE_NAME + unitType);
        return createUnit(unitClass);
    }

    private Unit createUnit(Class<Unit> unitClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Unit> constructor = unitClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }
}
