import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

public class Test13 {
    //FindProductByLabel
    @Test
    public void findByLabel_Should_Work_Correctly() {
        //Arrange
        ProductStock stock = new Instock();
        Product product1 = new Product("SalamShpekov", 3.50, 50);
        Product product2 = new Product("BekonNov", 2.65, 43);
        Product product3 = new Product("MayonezaNiskomaslena", 1.30, 13);

        //Act
        stock.add(product1);
        stock.add(product2);
        stock.add(product3);

        stock.changeQuantity("SalamShpekov", 3);
        //Assert
        Assert.assertTrue(stock.contains(product1));
        Assert.assertSame(product2, stock.findByLabel("BekonNov"));
    }

}
