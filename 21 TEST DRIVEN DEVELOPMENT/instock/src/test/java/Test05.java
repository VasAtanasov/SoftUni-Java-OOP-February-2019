import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

public class Test05 {
    @Test
    public void contains_On_Non_ExistingElement_ShouldReturnFalse() {
        //Arrange
        ProductStock stock = new Instock();
        Product product = new Product("Pizza", 4.30, 1510);
        Product product2 = new Product("Rizza", 4.30, 1510);

        //Act
        stock.add(product);

        //Assert
        boolean actual = stock.contains(product2);
        Assert.assertFalse(actual);

    }
}
