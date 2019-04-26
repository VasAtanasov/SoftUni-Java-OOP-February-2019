package Ex04WorkForce.io;


import Ex04WorkForce.interfaces.InputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Hristo Skipernov on 09/05/2017.
 */
public class ConsoleInputReader implements InputReader {
    private BufferedReader reader;

    public ConsoleInputReader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String readLine() {
        String line = null;
        try {
            line = this.reader.readLine();
        } catch (IOException ignored) {
        }
        return line;
    }
}
