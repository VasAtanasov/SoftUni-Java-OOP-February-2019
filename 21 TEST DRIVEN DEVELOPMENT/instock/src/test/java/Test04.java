import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

public class Test04 {
    //Contains
    @Test
    public void add_SingleElement_Contains_ShouldReturnTrue() {
        //Arrange
        ProductStock stock = new Instock();
        Product product = new Product("Pizza", 4.30, 1510);

        //Act
        stock.add(product);

        //Assert
        boolean actual = stock.contains(product);
        Assert.assertTrue(actual);
    }

}
