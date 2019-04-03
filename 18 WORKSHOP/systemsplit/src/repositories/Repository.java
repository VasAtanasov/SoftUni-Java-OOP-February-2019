package repositories;


import interfaces.Model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Repository<T extends Model> {
    private Map<String, T> data;
    private Map<String, T> dump;

    public Repository() {
        this.data = new LinkedHashMap<>();
        this.dump = new LinkedHashMap<>();
    }

    public boolean contains(String name) {
        return this.getByName(name) != null;
    }

    public void persist(T item) {
        this.data.putIfAbsent(item.getName(), item);
    }

    public T getByName(String name) {
        return this.data.get(name);
    }

    public int getCount() {
        return this.data.size();
    }

    public void remove(String name) {
        this.data.remove(name);
    }

    public Collection<T> getAll() {
        return this.data.values();
    }

    public Collection<T> getAllDumped() {
        return this.dump.values();
    }

    public void dump(String name) {
        T item = this.data.remove(name);
        if (name == null) {
            return;
        }
        this.dump.put(item.getName(), item);
    }

    public void restore(String name) {
        T item = this.dump.remove(name);
        if (name == null) {
            return;
        }
        this.data.put(item.getName(), item);
    }

    public void destroy(String name) {
        this.dump.remove(name);
    }
}
