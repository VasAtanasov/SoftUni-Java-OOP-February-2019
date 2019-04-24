import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Test;

public class Test17 {
    @Test(expected = IllegalArgumentException.class)
    public void findFirstByAlphabetical_On_WrongArgumentShouldThrow() {
        //Arrange
        ProductStock stock = new Instock();
        Product product5 = new Product("Jelqzo", 0.70, 20);
        Product product6 = new Product("Belina", .75, 50);
        Product product7 = new Product("Sirene", .77, 50);
        //Act
        stock.add(product5);
        stock.add(product6);
        stock.add(product7);
        //Assert

        stock.findFirstByAlphabeticalOrder(5).iterator().hasNext();
    }
}
