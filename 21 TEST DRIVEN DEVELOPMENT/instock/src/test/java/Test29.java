import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test29 {
    //FindAllByPrice
    @Test
    public void findByPrice_On_ExistingItems_ShouldReturn_Correct_Enumeration() {
        //Arrange
        ProductStock stock = new Instock();
        Product product1 = new Product("SalamShpekov", 2.65, 50);
        Product product2 = new Product("BekonNov", 2.65, 43);
        Product product3 = new Product("MayonezaNiskomaslena", 1.30, 13);
        Product product4 = new Product("Ketchup", 2.65, 73);
        Product product5 = new Product("Jelqzo", 0.70, 20);
        Product product6 = new Product("Belina", .75, 50);
        Product product7 = new Product("Sirene", .77, 50);
        Product[] expected = new Product[] {
                product1,product2,product4
        };
        //Act
        stock.add(product1);
        stock.add(product2);
        stock.add(product3);
        stock.add(product4);
        stock.add(product5);
        stock.add(product6);
        stock.add(product7);

        //Assert
        List<Product> result = new ArrayList<>();
        Iterable<Product> res = stock.findAllByPrice(2.65);
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
