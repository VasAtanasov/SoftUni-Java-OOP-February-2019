package Ex01EventImplementation;

public class Handler implements NameChangeListener {


    @Override
    public void handleChangedName(EventNameChange event) {
        System.out.println(String.format("Dispatcher's name changed to %s.", event.getNameChanged()));
    }
}
