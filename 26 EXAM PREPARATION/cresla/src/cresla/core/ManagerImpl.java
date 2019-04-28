package cresla.core;

import cresla.entities.modules.CooldownSystem;
import cresla.entities.modules.CryogenRod;
import cresla.entities.modules.HeatProcessor;
import cresla.entities.reactors.CryoReactor;
import cresla.entities.reactors.HeatReactor;
import cresla.interfaces.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerImpl implements Manager {
    private static final String REACTOR_CREATED = "Created %s Reactor - %d";
    private static final String MODULE_CREATED = "Added %s - %d to Reactor - %d";

    private enum Model {
        Heat, Cryo, CryogenRod, HeatProcessor, CooldownSystem
    }

    private Map<Integer, Reactor> reactors;
    private Map<Integer, Identifiable> byId;
    private int cryoReactorsCount;
    private int heatReactorsCount;
    private int energyModulesCount;
    private int absorbingModulesCount;

    public ManagerImpl() {
        this.reactors = new HashMap<>();
        this.byId = new HashMap<>();
        this.cryoReactorsCount = 0;
        this.heatReactorsCount = 0;
        this.energyModulesCount = 0;
        this.absorbingModulesCount = 0;
    }

    @Override
    public String reactorCommand(List<String> arguments) {
        Model type = Model.valueOf(arguments.get(0));
        int additionalParameter = Integer.parseInt(arguments.get(1));
        int moduleCapacity = Integer.parseInt(arguments.get(2));
        int id = Generator.generateId();
        Reactor reactor = null;
        switch (type) {
            case Cryo:
                reactor = new CryoReactor(id, additionalParameter, moduleCapacity);
                cryoReactorsCount++;
                break;
            case Heat:
                reactor = new HeatReactor(id, additionalParameter, moduleCapacity);
                heatReactorsCount++;
                break;
        }
        this.reactors.put(id, reactor);
        this.byId.put(id, reactor);
        return String.format(REACTOR_CREATED, type, id);
    }

    @Override
    public String moduleCommand(List<String> arguments) {
        int reactorId = Integer.parseInt(arguments.get(0));
        Model type = Model.valueOf(arguments.get(1));
        int additionalParameter = Integer.parseInt(arguments.get(2));
        int id = Generator.generateId();
        switch (type) {
            case CryogenRod:
                this.createModule(reactorId, new CryogenRod(id, additionalParameter));
                break;
            case HeatProcessor:
                this.createModule(reactorId, new HeatProcessor(id, additionalParameter));
                break;
            case CooldownSystem:
                this.createModule(reactorId, new CooldownSystem(id, additionalParameter));
                break;
        }
        return String.format(MODULE_CREATED, type, id, reactorId);
    }

    private void createModule(int reactorId, EnergyModule energyModule) {
        Reactor reactor = this.reactors.get(reactorId);
        if (reactor != null) {
            reactor.addEnergyModule(energyModule);
        }
        this.energyModulesCount++;
        this.byId.put(energyModule.getId(), energyModule);
    }

    private void createModule(int reactorId, AbsorbingModule absorbingModule) {
        Reactor reactor = this.reactors.get(reactorId);
        if (reactor != null) {
            reactor.addAbsorbingModule(absorbingModule);
        }
        this.absorbingModulesCount++;
        this.byId.put(absorbingModule.getId(), absorbingModule);
    }

    @Override
    public String reportCommand(List<String> arguments) {
        int id = Integer.parseInt(arguments.get(0));
        return this.byId.get(id).toString();
    }

    @Override
    public String exitCommand(List<String> arguments) {
        long energyOutput = this.reactors.values().stream().mapToLong(Reactor::getTotalEnergyOutput).sum();
        long heatAbsorbing = this.reactors.values().stream().mapToLong(Reactor::getTotalHeatAbsorbing).sum();
        return String.format("Cryo Reactors: %d\r\n" +
                        "Heat Reactors: %d\r\n" +
                        "Energy Modules: %d\r\n" +
                        "Absorbing Modules: %d\r\n" +
                        "Total Energy Output: %d\r\n" +
                        "Total Heat Absorbing: %d",
                this.cryoReactorsCount,
                this.heatReactorsCount,
                this.energyModulesCount,
                this.absorbingModulesCount,
                energyOutput,
                heatAbsorbing
        );
    }
}
