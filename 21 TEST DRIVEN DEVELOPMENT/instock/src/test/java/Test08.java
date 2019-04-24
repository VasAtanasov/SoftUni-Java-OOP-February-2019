import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

public class Test08 {
    @Test
    public void find_Product_On_ExistingProduct_ShouldWorkCorrectly() {
        //Arrange
        ProductStock stock = new Instock();
        Product product1 = new Product("Balsam", 5.3, 12);
        Product product2 = new Product("Korab", 12.6, 1255);
        Product product3 = new Product("Meduza", 53.1, 55);
        //Act
        stock.add(product1);
        stock.add(product2);
        stock.add(product3);
        //Assert
        Assert.assertSame(product3, stock.find(2));
        Assert.assertNotSame(product1, stock.find(1));
        Assert.assertSame(product1, stock.find(0));
    }

}
