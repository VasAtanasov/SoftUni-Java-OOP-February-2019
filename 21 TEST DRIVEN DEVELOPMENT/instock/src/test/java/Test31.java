import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test31 {
    @Test
    public void findByPrice_UnderFloatingPoint_PrecisionSurcumstances_ShouldNotFail() {
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
        //Assert
        List<Product> result = new ArrayList<>();
        Iterable<Product> res = stock.findAllByPrice(1.2999999);
        for (Product p : res) {
            result.add(p);
        }

        Product[] actual = new Product[result.size()];
        for(int i = 0 ; i < result.size(); i++){
            actual[i] = result.get(i);
        }
        Assert.assertArrayEquals(new Product[0], actual);
    }

}
