import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

public class Test06 {
    @Test
    public void contains_On_Empty_Collection_ShouldReturnFalse() {
        //Arrange
        ProductStock stock = new Instock();
        //Act
        //Assert
        Assert.assertFalse(stock.contains(new Product("Bakar", 5.5, 15)));
    }
}
