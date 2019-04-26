package Ex02KingGambit.interfaces;

public interface OutputWriter {
    void writeLine(String output);

    void writeLine(String format, Object... params);
}
