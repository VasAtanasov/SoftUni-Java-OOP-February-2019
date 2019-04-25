package models;

import interfaces.ObservableTarget;
import interfaces.Observer;

import java.util.LinkedHashSet;
import java.util.Set;

public class Dragon implements ObservableTarget {

    private static final String THIS_DIED_EVENT = "%s dies";

    private String id;
    private int hp;
    private int reward;
    private boolean eventTriggered;
    private Set<Observer> observers;

    public Dragon(String id, int hp, int reward) {
        this.id = id;
        this.hp = hp;
        this.reward = reward;
        this.observers = new LinkedHashSet<>();
    }

    @Override
    public void receiveDamage(int dmg) {
        if (! this.isDead()) {
            this.hp -= dmg;
        }

        if (this.isDead() && ! this.eventTriggered) {
            System.out.println(String.format(THIS_DIED_EVENT, this));
            this.eventTriggered = true;
            this.notifyObservers();
        }
    }

    @Override
    public boolean isDead() {
        return this.hp <= 0;
    }

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public void register(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        this.observers.forEach(observer -> observer.update(this.reward));
    }
}
