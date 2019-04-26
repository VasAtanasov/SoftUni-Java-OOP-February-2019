package hell.interfaces;

import java.util.List;

public interface Manager {
    String addHero(List<String> arguments);

    String addItem(List<String> arguments);

    String addRecipe(List<String> arguments);

    String inspect(List<String> arguments) throws NoSuchFieldException, IllegalAccessException;

    String quit() throws NoSuchFieldException, IllegalAccessException;
}
