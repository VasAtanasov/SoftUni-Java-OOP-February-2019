package core;

import interfaces.ComponentFactory;
import interfaces.Controller;
import interfaces.Model;
import models.hardwares.Hardware;
import models.softwares.Software;
import repositories.HardwareRepository;
import repositories.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MainController implements Controller {
    private static final String HARDWARE_MODELS_PACKAGE = "models.hardwares.";
    private static final String SOFTWARE_MODELS_PACKAGE = "models.softwares.";
    private static final String EXPRESS_SOFTWARE = "ExpressSoftware";
    private static final String LIGHT_SOFTWARE = "LightSoftware";
    private static final String POWER_HARDWARE = "PowerHardware";
    private static final String HEAVY_HARDWARE = "HeavyHardware";


    private Repository<Hardware> hardwareRepository;

    public MainController() {
        this.hardwareRepository = new HardwareRepository();
    }

    @Override
    public void createHardware(String type, String name, long capacity, long memory) throws
            ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {

        if (this.hardwareRepository.contains(name)) {
            return;
        }
        Hardware hardware = ComponentFactory.create(this.getPackageName(HARDWARE_MODELS_PACKAGE, type), name, capacity, memory);
        this.hardwareRepository.persist(hardware);
    }

    @Override
    public void createSoftware(String type, String hardwareComponent, String name, long capacity, long memory) throws
            ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {

        Software software = ComponentFactory.create(this.getPackageName(SOFTWARE_MODELS_PACKAGE, type), name, capacity, memory);

        Hardware hardware = this.hardwareRepository.getByName(hardwareComponent);
        if (hardware == null) {
            return;
        }
        hardware.install(software);
    }

    @Override
    public void releaseSoftwareComponent(String hardwareComponentName, String softwareComponentName) {
        Hardware hardware = this.hardwareRepository.getByName(hardwareComponentName);
        if (hardware == null) {
            return;
        }
        hardware.uninstall(softwareComponentName);
    }

    @Override
    public String analyze() {
        Collection<Hardware> hardwares = this.hardwareRepository.getAll();
        Collection<Software> software = hardwares.stream()
                .flatMap(hardware -> hardware.getComponents().stream())
                .collect(Collectors.toList());

        long maxCapacityAvailable = hardwares.stream().mapToLong(Hardware::getMaxCapacity).sum();
        long maxMemoryAvailable = hardwares.stream().mapToLong(Hardware::getMaxMemory).sum();

        long softwareCount = hardwares.stream().mapToLong(Hardware::getInstalledSoftwaresCount).sum();

        long softwareMemoryConsumption = software
                .stream()
                .mapToLong(Software::getMemoryConsumption)
                .sum();

        long softwareCapacityConsumption = software
                .stream()
                .mapToLong(Software::getCapacityConsumption)
                .sum();

        return "System Analysis\n" + String.format("Hardware Components: %d\n", this.hardwareRepository.getCount()) +
                String.format("Software Components: %d\n", softwareCount) +
                String.format("Total Operational Memory: %d / %d\n", softwareMemoryConsumption, maxMemoryAvailable) +
                String.format("Total Capacity Taken: %d / %d", softwareCapacityConsumption, maxCapacityAvailable);
    }

    @Override
    public String systemStatistics() {
        List<Hardware> hardwares = this.hardwareRepository.getAll().stream()
                .sorted((a, b) -> {
                    if (a.getClass().getSimpleName().equals(POWER_HARDWARE)) {
                        return - 1;
                    }
                    if (b.getClass().getSimpleName().equals(HEAVY_HARDWARE)) {
                        return 1;
                    }
                    return 0;
                })
                .collect(Collectors.toList());

        if (hardwares.isEmpty()) {
            return null;
        }

        StringBuilder output = new StringBuilder();
        for (Hardware hardware : hardwares) {
            output.append(String.format("Hardware Component - %s\n", hardware.getName()));
            long expressSoftwareCount = this.getComponentCount(hardware.getComponents(), EXPRESS_SOFTWARE);
            output.append(String.format("Express Software Components - %d\n", expressSoftwareCount));
            long lightSoftwareCount = this.getComponentCount(hardware.getComponents(), LIGHT_SOFTWARE);
            output.append(String.format("Light Software Components - %d\n", lightSoftwareCount));
            output.append(String.format("Memory Usage: %d / %d\n", hardware.getOccupiedMemory(), hardware.getMaxMemory()));
            output.append(String.format("Capacity Usage: %d / %d\n", hardware.getOccupiedCapacity(), hardware.getMaxCapacity()));
            output.append(String.format("Type: %s\n", hardware.getType()));
            if (hardware.getComponents().isEmpty()) {
                output.append("Software Components: None\n");
            } else {
                String components = hardware.getComponents().stream()
                        .map(Model::getName)
                        .collect(Collectors.joining(", "));
                output.append(String.format("Software Components: %s\n", components));
            }
        }
        return output.toString().trim();
    }

    @Override
    public void dumpHardware(String hardwareComponentName) {
        this.hardwareRepository.dump(hardwareComponentName);
    }

    @Override
    public void restoreHardware(String hardwareComponentName) {
        this.hardwareRepository.restore(hardwareComponentName);
    }

    @Override
    public void destroyHardware(String hardwareComponentName) {
        this.hardwareRepository.destroy(hardwareComponentName);
    }

    @Override
    public String dumpAnalyze() {
        Collection<Hardware> dumped = this.hardwareRepository.getAllDumped();
        Collection<Software> softwareDumped = dumped.stream()
                .flatMap(hardware -> hardware.getComponents().stream())
                .collect(Collectors.toList());

        long powerHardwareCont = this.getComponentCount(dumped, POWER_HARDWARE);
        long heavyHardwareCount = this.getComponentCount(dumped, HEAVY_HARDWARE);
        long expressSoftwareCount = this.getComponentCount(softwareDumped, EXPRESS_SOFTWARE);
        long lightSoftwareCount = this.getComponentCount(softwareDumped, LIGHT_SOFTWARE);
        long maxMemoryDumped = softwareDumped.stream().mapToLong(Software::getMemoryConsumption).sum();
        long maxCapacityDumped = softwareDumped.stream().mapToLong(Software::getCapacityConsumption).sum();

        return "Dump Analysis\n" + String.format("Power Hardware Components: %d\n", powerHardwareCont) +
                String.format("Heavy Hardware Components: %d\n", heavyHardwareCount) +
                String.format("Express Software Components: %d\n", expressSoftwareCount) +
                String.format("Light Software Components: %d\n", lightSoftwareCount) +
                String.format("Total Dumped Memory: %d\n", maxMemoryDumped) +
                String.format("Total Dumped Capacity: %d", maxCapacityDumped);
    }

    private String getPackageName(String packageName, String model) {
        return packageName + model;
    }

    private <T extends Model> long getComponentCount(Collection<T> components, String type) {
        return components.stream()
                .filter(c -> c.getClass().getSimpleName().equals(type))
                .count();
    }
}
