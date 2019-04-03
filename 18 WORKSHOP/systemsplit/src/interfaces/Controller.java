package interfaces;


import java.lang.reflect.InvocationTargetException;

public interface Controller {

    void createHardware(String type, String name, long capacity, long memory) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    void createSoftware(String type, String hardware, String name, long capacity, long memory) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    void releaseSoftwareComponent(String hardwareComponentName, String softwareComponentName);

    String analyze();

    String systemStatistics();

    void dumpHardware(String hardwareComponentName);

    void restoreHardware(String hardwareComponentName);

    void destroyHardware(String hardwareComponentName);

    String dumpAnalyze();
}
