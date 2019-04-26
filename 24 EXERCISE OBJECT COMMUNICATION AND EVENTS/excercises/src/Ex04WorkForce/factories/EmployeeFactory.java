package Ex04WorkForce.factories;

import Ex04WorkForce.interfaces.Employee;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class EmployeeFactory {
    private static final String EMPLOYEES_PACKAGE_NAME = "Ex04WorkForce.employees.";

    private EmployeeFactory(){}

    public static Employee createEmployee(String name, String unitType) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class<?> employeeClass = Class.forName(EMPLOYEES_PACKAGE_NAME + unitType);
        Constructor<?> employeeConstructor = employeeClass.getDeclaredConstructor(String.class);
        Employee employee = (Employee) employeeConstructor.newInstance(name);
        return employee;
    }
}
