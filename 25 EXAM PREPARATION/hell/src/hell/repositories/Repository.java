package hell.repositories;

import hell.interfaces.Hero;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class Repository<T extends Hero> {
    private Map<String, T> data;

    public Repository() {
        this.data = new HashMap<>();
    }

    public void persist(T item) {
        this.data.put(item.getName(), item);
    }

    public T getByName(String model) {
        return this.data.get(model);
    }

    public Collection<T> getAll() {
        return this.data.values();
    }
}
