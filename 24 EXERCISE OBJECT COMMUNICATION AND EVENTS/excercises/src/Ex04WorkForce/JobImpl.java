package Ex04WorkForce;

import Ex04WorkForce.interfaces.Employee;
import Ex04WorkForce.interfaces.Job;

public class JobImpl implements Job {
    private String name;
    private int hoursPerWeekRequired;
    private Employee employee;

    public JobImpl(String name, int hoursPerWeekRequired, Employee employee) {
        this.name = name;
        this.hoursPerWeekRequired = hoursPerWeekRequired;
        this.employee = employee;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getHoursPerWeekRequired() {
        return this.hoursPerWeekRequired;
    }

    @Override
    public Employee getEmployee() {
        return this.employee;
    }

    @Override
    public void update(){
        this.hoursPerWeekRequired -= this.employee.getWorkHoursPerWeek();
    }

    @Override
    public String toString(){
        return String.format("Job: %s Hours Remaining: %d",
                this.name, this.hoursPerWeekRequired);
    }
}
