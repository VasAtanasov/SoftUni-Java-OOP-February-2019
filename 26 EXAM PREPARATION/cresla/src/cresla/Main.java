package cresla;

import cresla.core.Engine;
import cresla.core.ManagerImpl;
import cresla.interfaces.InputReader;
import cresla.interfaces.Manager;
import cresla.interfaces.OutputWriter;
import cresla.io.InputReaderImpl;
import cresla.io.OutputWriterImpl;

public class Main {
    public static void main(String[] args) {
        InputReader reader = new InputReaderImpl();
        OutputWriter writer = new OutputWriterImpl();
        Manager manager = new ManagerImpl();
        Engine engine = new Engine(reader, writer, manager);
        engine.run();
    }
}
