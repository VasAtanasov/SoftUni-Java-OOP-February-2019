package cresla.interfaces;

import java.util.List;

public interface Command {
    String execute(List<String> arguments);
}
