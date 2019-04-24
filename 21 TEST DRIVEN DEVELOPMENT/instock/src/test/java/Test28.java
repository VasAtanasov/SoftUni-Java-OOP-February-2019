import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test28 {
    @Test
    public void findAllByPriceRange_LowerEndExclusive_HigherEndInclusive_ShouldWorkCorrectly() {
        //Arrange
        ProductStock stock = new Instock();
        Product product1 = new Product("SalamShpekov", 3.50, 50);
        Product product2 = new Product("Kurban", 2.0, 2);
        //Act
        stock.add(product1);
        stock.add(product2);
        //Assert
        Product[] expected = new Product[]{
                product1
        };
        List<Product> result = new ArrayList<>();
        Iterable<Product> res = stock.findAllInRange(2.0, 3.50);
        for (Product p : res) {
            result.add(p);
        }

        Product[] actual = new Product[result.size()];
        for(int i = 0 ; i < result.size(); i++){
            actual[i] = result.get(i);
        }
        Assert.assertArrayEquals(expected, actual);
    }

}
