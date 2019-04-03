package core;

import exceptions.DuplicateModelException;
import exceptions.NonExistentModelException;
import models.boats.Boat;
import models.engines.BoatEngine;
import repositories.BoatRepository;
import repositories.BoatEngineRepository;

public class Database {
    private BoatRepository boatRepository;
    private BoatEngineRepository boatEngineRepository;

    public Database() {
        this.boatRepository = new BoatRepository();
        this.boatEngineRepository = new BoatEngineRepository();
    }

    public void saveAndFlush(Boat boat) throws DuplicateModelException {
        this.boatRepository.persist(boat);
    }

    public void saveAndFlush(BoatEngine boatEngine) throws DuplicateModelException {
        this.boatEngineRepository.persist(boatEngine);
    }

    public BoatEngine getBoatEngine(String model) throws NonExistentModelException {
        return this.boatEngineRepository.getByModel(model);
    }

    public Boat getBoat(String model) throws NonExistentModelException {
        return this.boatRepository.getByModel(model);
    }
}
