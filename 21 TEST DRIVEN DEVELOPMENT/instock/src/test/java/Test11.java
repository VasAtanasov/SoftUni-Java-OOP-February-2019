import instock.Instock;
import instock.Product;
import instock.ProductStock;

import org.junit.Test;

public class Test11 {

    @Test(expected = IllegalArgumentException.class)
    public void changeQuantity_On_NonExisting_Product_ShouldThrow() {
        //Arrange
        ProductStock stock = new Instock();
        Product product1 = new Product("SalamShpekov", 3.50, 50);
        Product product2 = new Product("BekonNov", 2.65, 43);
        Product product3 = new Product("MayonezaNiskomaslena", 1.30, 13);

        //Act

        //Assert
        stock.changeQuantity("Barekov", 0);
    }
}
