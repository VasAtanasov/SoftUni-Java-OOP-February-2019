package Ex04WorkForce.io;


import Ex04WorkForce.interfaces.OutputWriter;

/**
 * Created by Hristo Skipernov on 09/05/2017.
 */
public class ConsoleOutputWriter implements OutputWriter {
    @Override
    public void writeLine(String output) {
        System.out.println(output);
    }

    @Override
    public void writeLine(String format, Object... params) {
        System.out.println(String.format(format, params));
    }
}
