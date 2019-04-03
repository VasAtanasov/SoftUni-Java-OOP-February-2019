package repositories;

import exceptions.DuplicateModelException;
import exceptions.NonExistentModelException;
import interfaces.Model;

import java.util.HashMap;
import java.util.Map;

public abstract class Repository<T extends Model> {
    private Map<String, T> data;

    public Repository() {
        this.data = new HashMap<>();
    }

    public void persist(T item) throws DuplicateModelException {
        if (this.data.containsKey(item.getModel())) {
            throw new DuplicateModelException();
        }
        this.data.put(item.getModel(), item);
    }

    public T getByModel(String model) throws NonExistentModelException {
        T item = this.data.get(model);
        if (item == null) {
            throw new NonExistentModelException();
        }
        return item;
    }
}
