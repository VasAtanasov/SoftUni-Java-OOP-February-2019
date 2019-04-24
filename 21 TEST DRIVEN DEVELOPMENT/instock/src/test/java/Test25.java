import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

public class Test25 {
    //Enumerator
    @Test
    public void enumerator_ShouldReturn_ProductsInInsertionOrder_After_Adding_Multiple() {
        //Arrange
        ProductStock stock = new Instock();
        Product product1 = new Product("SalamShpekov", 3.50, 50);
        Product product2 = new Product("BekonNov", 2.65, 43);
        Product product3 = new Product("MayonezaNiskomaslena", 1.30, 13);
        Product product4 = new Product("Ketchup", 1.80, 73);
        Product product5 = new Product("Jelqzo", 0.70, 20);
        Product product6 = new Product("Belina", .75, 50);
        Product product7 = new Product("Sirene", .77, 50);
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
                product1,product2,
                product3,product4,
                product5,product6,
                product7,
        };
        Product[] actual = new Product[stock.getCount()];
        int i = 0 ;
        for(Product p : stock){
            actual[i] = p;
            i++;
        }
        Assert.assertArrayEquals(expected, actual);
    }


}
