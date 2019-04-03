package core;

import interfaces.Controller;
import interfaces.OutputWriter;

import java.lang.reflect.InvocationTargetException;

public class CommandHandlerImpl implements interfaces.CommandHandler {
    private static final String REGISTER = "Register";
    private static final String SPACE = "";

    private enum Command {
        RegisterPowerHardware,
        RegisterHeavyHardware,
        RegisterExpressSoftware,
        RegisterLightSoftware,
        ReleaseSoftwareComponent,
        Analyze,
        SystemSplit,
        Dump,
        Restore,
        Destroy,
        DumpAnalyze,
    }

    private Controller controller;
    private OutputWriter writer;

    public CommandHandlerImpl(OutputWriter writer) {
        this.controller = new MainController();
        this.writer = writer;
    }

    @Override
    public void interpretCommand(String[] data) throws
            ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {

        Command command = Command.valueOf(data[0]);

        switch (command) {
            case RegisterPowerHardware:
            case RegisterHeavyHardware:
                this.registerHardware(data);
                break;
            case RegisterExpressSoftware:
            case RegisterLightSoftware:
                this.registerSoftware(data);
                break;
            case ReleaseSoftwareComponent:
                this.releaseSoftwareComponent(data);
                break;
            case Analyze:
                this.analyze();
                break;
            case SystemSplit:
                this.systemSplit();
                break;
            case Dump:
                this.dumpHardware(data);
                break;
            case Restore:
                this.restoreHardware(data);
                break;
            case Destroy:
                this.destroyHardware(data);
                break;
            case DumpAnalyze:
                this.dumpAnalyze();
                break;
        }
    }

    private void dumpAnalyze() {
        this.writer.writeLine(this.controller.dumpAnalyze());
    }

    private void destroyHardware(String[] data) {
        String hardwareComponentName = data[1];
        this.controller.destroyHardware(hardwareComponentName);
    }

    private void restoreHardware(String[] data) {
        String hardwareComponentName = data[1];
        this.controller.restoreHardware(hardwareComponentName);
    }

    private void dumpHardware(String[] data) {
        String hardwareComponentName = data[1];
        this.controller.dumpHardware(hardwareComponentName);
    }

    private void systemSplit() {
        String output = this.controller.systemStatistics();
        if (output == null) {
            return;
        }
        this.writer.writeLine(this.controller.systemStatistics());
    }

    private void analyze() {
        this.writer.writeLine(this.controller.analyze());
    }

    private void releaseSoftwareComponent(String[] data) {
        String hardwareComponentName = data[1];
        String softwareComponentName = data[2];

        this.controller.releaseSoftwareComponent(hardwareComponentName, softwareComponentName);
    }

    private void registerSoftware(String[] data) throws
            ClassNotFoundException,
            NoSuchMethodException,
            InstantiationException,
            IllegalAccessException,
            InvocationTargetException {

        String type = this.mapString(data[0]);
        String hardware = data[1];
        String name = data[2];
        long capacity = Long.parseLong(data[3]);
        long memory = Long.parseLong(data[4]);

        this.controller.createSoftware(type, hardware, name, capacity, memory);
    }

    private void registerHardware(String[] data) throws
            ClassNotFoundException,
            NoSuchMethodException,
            InstantiationException,
            IllegalAccessException,
            InvocationTargetException {

        String type = this.mapString(data[0]);
        String name = data[1];
        long capacity = Long.parseLong(data[2]);
        long memory = Long.parseLong(data[3]);

        this.controller.createHardware(type, name, capacity, memory);
    }

    private String mapString(String command) {
        return command.replace(REGISTER, SPACE);
    }
}
