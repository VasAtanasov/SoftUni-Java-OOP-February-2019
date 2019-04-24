import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test12 {
    @Test
    public void changeQuantity_On_Multiple_Elements_ShouldWorkCorrectly() {
        //Arrange
        ProductStock stock = new Instock();
        Product product1 = new Product("SalamShpekov", 3.50, 560);
        Product product2 = new Product("BekonNov", 2.65, 43);
        Product product3 = new Product("MayonezaNiskomaslena", 1.30, 13);
        Product product4 = new Product("Ketchup", 1.80, 73);
        Product product5 = new Product("Jelqzo", 0.70, 130);
        Product product6 = new Product("Belina", .75, 240);
        Product product7 = new Product("Sirene", .77, 30);

        //Act
        stock.add(product1);
        stock.add(product2);
        stock.add(product3);
        stock.add(product4);
        stock.add(product5);
        stock.add(product6);
        stock.add(product7);

        stock.changeQuantity(product4.getLabel(), 50);
        stock.changeQuantity(product7.getLabel(), 50);
        stock.changeQuantity(product3.getLabel(), 50);

        //Assert
        Product[] expected = new Product[]{
                product4,product7,product3
        };
        List<Product> result = new ArrayList<>();

        Iterable<Product> res = stock.findAllByQuantity(50);
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
