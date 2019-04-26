package hell.io;

import hell.interfaces.InputReader;

import java.util.Scanner;

public class InputReaderImpl implements InputReader {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String readLine() {
        return this.scanner.nextLine();
    }
}
