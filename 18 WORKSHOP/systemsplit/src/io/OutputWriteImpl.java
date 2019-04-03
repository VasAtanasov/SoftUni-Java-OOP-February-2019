package io;

import interfaces.OutputWriter;

public class OutputWriteImpl implements OutputWriter {
    @Override
    public void writeLine(String output) {
        System.out.println(output);
    }
}
