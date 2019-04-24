import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

public class Test07 {

    //FindProductByIndex (InsertionOrder)
    @Test(expected = IndexOutOfBoundsException.class)
    public void find_Product_WrongIndex_ShouldThrow() {
        //Arrange
        ProductStock stock = new Instock();
        Product product = new Product("Salam", 2.50, 50);
        //Act
        stock.add(product);
        //Assert
        boolean throwed = false;
        try{
            stock.find(-5);
        }
        catch(IndexOutOfBoundsException ex){
            throwed = true;
        }
        Assert.assertTrue(throwed);

        stock.find(1);
    }
}
