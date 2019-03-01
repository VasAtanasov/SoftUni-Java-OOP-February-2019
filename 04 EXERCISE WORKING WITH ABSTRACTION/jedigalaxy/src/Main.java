import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static BufferedReader reader;
    private static int rows, cols;
    private static long sum;
    private static Set<Integer> evilPoints;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        evilPoints = new HashSet<>();
        sum = 0L;
    }

    public static void main(String[] args) throws IOException {
        getMatrixDimension();

        String input;
        while (! "Let the Force be with you".equals(input = reader.readLine())) {
            String[] dimension = input.split("\\s+");
            int jediRow = Integer.parseInt(dimension[0]);
            int jediCol = Integer.parseInt(dimension[1]);

            dimension = reader.readLine().split("\\s+");
            int evilRow = Integer.parseInt(dimension[0]);
            int evilCol = Integer.parseInt(dimension[1]);

            calculateEvilPath(evilRow, evilCol);
            calculateJediPath(jediRow, jediCol);
        }

        System.out.println(sum);
    }

    private static void calculateJediPath(int jediRow, int jediCol) {
        for (; jediRow >= 0 && jediCol < cols; jediRow--, jediCol++) {
            if (inRange(jediRow,jediCol)) {
                int cellValue = cellValue(jediRow, jediCol);
                if (! evilPoints.contains(cellValue)) {
                    sum += cellValue;
                }
            }
        }
    }

    private static void calculateEvilPath(int evilRow, int evilCol) {
        for (; evilRow >= 0 && evilCol >= 0; evilRow--, evilCol--) {
            if (inRange(evilRow,evilCol)) {
                evilPoints.add(cellValue(evilRow, evilCol));
            }
        }
    }

    private static boolean inRange(int row, int col) {
        return row >= 0 && row < rows &&
                col >= 0 && col < cols;
    }

    private static int cellValue(int row, int col) {
        return row * cols + col;
    }

    private static void getMatrixDimension() throws IOException {
        String[] dimension = reader.readLine().split("\\s+");
        rows = Integer.parseInt(dimension[0]);
        cols = Integer.parseInt(dimension[1]);
    }
}
