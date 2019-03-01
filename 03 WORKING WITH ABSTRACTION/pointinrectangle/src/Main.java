import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        int[] params = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        Point topLeft = new Point(params[0], params[1]);
        Point bottomRight = new Point(params[2], params[3]);

        Rectangle rectangle = new Rectangle(topLeft, bottomRight);

        int lines = Integer.parseInt(reader.readLine());

        for (int i = 0; i < lines; i++) {
            int[] pointParams = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            Point point = new Point(pointParams[0], pointParams[1]);

            System.out.println(rectangle.contains(point));
        }
    }
}
