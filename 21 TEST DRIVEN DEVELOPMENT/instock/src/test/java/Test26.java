import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

public class Test26 {
    @Test
    public void enumerator_ShouldReturn_EmptyEnumeration_On_Empty_Stock() {
        //Arrange
        ProductStock stock = new Instock();
        //Act
        //Assert
        Product[] expected = new Product[0];
        Product[] actual = new Product[stock.getCount()];
        int i = 0 ;
        for(Product product : stock){
            actual[i] = product;
            i++;
        }
        Assert.assertArrayEquals(expected, actual);
    }
}
