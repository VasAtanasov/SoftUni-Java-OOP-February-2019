import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

public class Test09 {

    @Test
    public void fdd_Single_Product_ShouldBeAt_0_Index() {
        //Arrange
        ProductStock stock = new Instock();
        Product product = new Product("Salam", 2.50, 50);
        //Act
        stock.add(product);
        //Assert
        Assert.assertEquals(product, stock.find(0));
    }
}
