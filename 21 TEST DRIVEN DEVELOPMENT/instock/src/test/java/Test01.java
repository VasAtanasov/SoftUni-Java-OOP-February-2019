import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;


public class Test01 {

    @Test
    public void add_MultipleElements_ShouldWorkCorrectly() {
        //Arrange
        ProductStock stock = new Instock();
        Product product1 = new Product("Salam", 2.50, 50);
        Product product2 = new Product("Bekon", 2.65, 43);
        Product product3 = new Product("Mayoneza", 1.30, 13);

        //Act
        stock.add(product1);
        stock.add(product2);
        stock.add(product3);

        //Assert
        Assert.assertEquals(product1, stock.find(0));
        Assert.assertEquals(product2, stock.find(1));
        Assert.assertEquals(product3, stock.find(2));
    }

}


