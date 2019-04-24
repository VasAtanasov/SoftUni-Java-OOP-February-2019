import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Test;

public class Test14 {
    @Test(expected = IllegalArgumentException.class)
    public void findByLabel_NoExistingProduct_ShouldThrow() {
        //Arrange
        ProductStock stock = new Instock();
        Product product1 = new Product("SalamShpekov", 3.50, 50);
        Product product2 = new Product("BekonNov", 2.65, 43);
        Product product3 = new Product("MayonezaNiskomaslena", 1.30, 13);

        //Act
        stock.add(product1);
        stock.add(product2);
        stock.add(product3);
        //Assert
        stock.findByLabel("BoroTreti");
    }
}
