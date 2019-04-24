import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test15 {
    //One more mby
    //FindFirstByAlphabeticalOrder
    @Test
    public void findFirstByAlphabeticalOrder_Should_ReturnCorrectEnumeration() {
        //Arrange
        ProductStock stock = new Instock();
        Product product1 = new Product("Abra", 3.50, 50);
        Product product2 = new Product("Bobar", 2.65, 43);
        Product product3 = new Product("Caza", 1.30, 13);
        Product product4 = new Product("Darfield", 1.80, 73);
        Product product5 = new Product("Eil*", 0.70, 20);
        Product product6 = new Product("Flen", .75, 50);
        Product product7 = new Product("Giilqzo", .77, 50);
        //Act
        stock.add(product1);
        stock.add(product2);
        stock.add(product3);
        stock.add(product4);
        stock.add(product5);
        stock.add(product6);
        stock.add(product7);
        //Assert
        Product[] expected = new Product[] {
                product1,product2,product3,product4,product5,product6,product7
        };
        Iterable<Product> res = stock.findFirstByAlphabeticalOrder(stock.getCount());
        List<Product> result = new ArrayList<>();
        for(Product p : res){
            result.add(p);
        }
        Product[] actual = new Product[result.size()];
        for(int i = 0 ; i < result.size(); i++){
            actual[i] = result.get(i);
        }
        Assert.assertArrayEquals(expected, actual);
    }

}
