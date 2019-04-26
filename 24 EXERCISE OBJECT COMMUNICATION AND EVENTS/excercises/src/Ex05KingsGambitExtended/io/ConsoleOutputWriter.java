package Ex05KingsGambitExtended.io;

import Ex05KingsGambitExtended.interfaces.OutputWriter;

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
