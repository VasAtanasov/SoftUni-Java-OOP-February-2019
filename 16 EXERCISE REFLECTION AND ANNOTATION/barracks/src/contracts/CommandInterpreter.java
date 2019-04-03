package contracts;

public interface CommandInterpreter {

	Executable interpretCommand(String[] data, String commandName);
}
