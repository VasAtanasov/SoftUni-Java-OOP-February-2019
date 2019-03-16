import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String author = reader.readLine();
        String title = reader.readLine();
        double price = Double.valueOf(reader.readLine());

        try {
            Book book = new Book(author,title,price);
            GoldenEditionBook goldenEditionBook = new GoldenEditionBook(author,title,price);

            System.out.println(book);
            System.out.println(goldenEditionBook);
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
    }
}
