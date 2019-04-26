package Ex01EventImplementation;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {

    private String name;
    private List<NameChangeListener> nameChangeListenerList;

    public Dispatcher() {
        this.nameChangeListenerList = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
        EventNameChange event = new EventNameChange(name);
        this.fireNameChangeEvent(event);
    }

    public void addNameChangeListener(NameChangeListener nameChangeListener){
        this.nameChangeListenerList.add(nameChangeListener);
    }

    public void removeNameChangeListener(NameChangeListener nameChangeListener){
        this.nameChangeListenerList.remove(nameChangeListener);
    }

    public void fireNameChangeEvent(EventNameChange event){
        for (NameChangeListener nameChangeListener : this.nameChangeListenerList) {
            nameChangeListener.handleChangedName(event);
        }
    }
}
