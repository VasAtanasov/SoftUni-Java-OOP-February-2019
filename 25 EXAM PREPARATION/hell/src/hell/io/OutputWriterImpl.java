package hell.io;

import hell.interfaces.OutputWriter;

public class OutputWriterImpl implements OutputWriter {
    @Override
    public void writeLine(String output) {
        System.out.println(output);
    }

    @Override
    public void writeLine(String format, Object... params) {

    }
}
