package Ex04WorkForce.employees;

import Ex04WorkForce.abstractClasses.Employee;

public class StandartEmployee extends Employee {
    private static final int WORK_HOURS_PER_WEEK_STANDARD_EMPLOYEE = 40;

    public StandartEmployee(String name) {
        super(name, WORK_HOURS_PER_WEEK_STANDARD_EMPLOYEE);
    }
}
