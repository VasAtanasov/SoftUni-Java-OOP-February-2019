package models;

import interfaces.Model;

public abstract class ModelImpl implements Model {
    protected static final long MAX_INDEX = 100;

    private String name;
    private String type;

    public ModelImpl(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
