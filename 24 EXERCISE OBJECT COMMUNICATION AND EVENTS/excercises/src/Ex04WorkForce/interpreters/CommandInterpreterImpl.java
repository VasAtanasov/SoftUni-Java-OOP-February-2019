package Ex04WorkForce.interpreters;


import Ex04WorkForce.JobImpl;
import Ex04WorkForce.factories.EmployeeFactory;
import Ex04WorkForce.interfaces.Employee;
import Ex04WorkForce.interfaces.Job;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CommandInterpreterImpl {

    private Map<String, Job> jobMap;
    private Map<String, Employee> employeeMap;

    public CommandInterpreterImpl() {
        this.jobMap = new LinkedHashMap<>();
        this.employeeMap = new LinkedHashMap<>();
    }


    public void createEmployee(String name, String employeeType) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Employee employee = EmployeeFactory.createEmployee(name, employeeType);
        employeeMap.putIfAbsent(name, employee);
    }

    public void createJob(String jobName, int hoursOfWorkRequired, String employeeName) {
        Employee employee = employeeMap.get(employeeName);
        Job job = new JobImpl(jobName, hoursOfWorkRequired, employee);
        jobMap.putIfAbsent(jobName, job);
    }

    public void jobsUpdate() {
        List<Job> jobsToRemove = new ArrayList<>();
        for (Job job : jobMap.values()) {
            job.update();
            if (job.getHoursPerWeekRequired() <= 0) {
                System.out.printf("Job %s done!%n", job.getName());
                jobsToRemove.add(job);
            }
        }

        for (Job job : jobsToRemove) {
            jobMap.remove(job.getName());
        }
    }

    public void printJobStatus() {
        for (Job job : jobMap.values()) {
            System.out.println(job);
        }
    }
}
