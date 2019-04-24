import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test19 {
    //FindFirstMostExpensiveItems
    @Test
    public void findFirstMostExpensiveProducts_Should_Return_CorrectEnumeration() {
        //Arrange
        ProductStock stock = new Instock();
        Product product2 = new Product("BekonNov", 2.65, 43);
        Product product3 = new Product("MayonezaNiskomaslena", 1.30, 13);
        Product product1 = new Product("SalamShpekov", 3.50, 50);
        //Act
        stock.add(product1);
        stock.add(product2);
        stock.add(product3);
        //Assert
        Product[] expected = new Product[] {
                product1,product2
        };
        List<Product> result = new ArrayList<>();
        Iterable<Product> res = stock.findFirstMostExpensiveProducts(2);
        for(Product p : res){
            result.add(p);
        }
        Product[] actual = new Product[result.size()];
        for(int i = 0 ; i < result.size(); i++){
            actual[i] = result.get(i);
        }
        Assert.assertArrayEquals(expected, actual);
        expected = new Product[] {
                product1
        };
        result = new ArrayList<>();
        res =  stock.findFirstMostExpensiveProducts(1);
        for(Product p : res){
            result.add(p);
        }
        actual = new Product[result.size()];
        for(int i = 0 ; i < result.size(); i++){
            actual[i] = result.get(i);
        }
        Assert.assertArrayEquals(expected, actual);
    }


}
