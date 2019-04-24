import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

public class Test03 {
    @Test
    public void add_MultipleElements_Should_Increase_Count() {
        //Arrange
        ProductStock stock = new Instock();
        Product product1 = new Product("Getter", 20.5, 15);
        Product product2 = new Product("OtherPRoduct", 206.1, 67);
        Product product3 = new Product("50CentPoster", 50, 50);
        //Act
        stock.add(product1);
        stock.add(product2);
        stock.add(product3);
        //Assert
        Assert.assertEquals(3, stock.getCount());
    }
}
