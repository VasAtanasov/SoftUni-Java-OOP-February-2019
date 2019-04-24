import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

public class Test10 {
    //ChangeQuantity
    @Test
    public void changeQuantity_On_ExistingProduct_ShouldWorkCorrectly() {
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
        int expected = 3;
        int actual = stock.findByLabel("SalamShpekov").getQuantity();
        //Assert
        Assert.assertEquals(3, stock.getCount());
        Assert.assertEquals(expected, actual);
    }
}
