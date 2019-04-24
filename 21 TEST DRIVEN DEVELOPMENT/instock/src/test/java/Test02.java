import instock.Instock;
import instock.Product;
import instock.ProductStock;
import org.junit.Assert;
import org.junit.Test;

public class Test02 {
    //Addition
    @Test
    public void add_Single_Product_ShouldAddProduct() {

        //Arrange
        ProductStock stock = new Instock();
        Product product = new Product("Salam", 2.50, 50);

        //Act
        stock.add(product);

        //Assert
        boolean pass = false;
        for(Product item : stock)
        {
            if (item == product
                    && (product.getPrice() == item.getPrice()
                    && product.getLabel().equals(item.getLabel())
                    && product.getQuantity() == item.getQuantity())) {
                pass = true;
            }
        }

        Assert.assertEquals(true, pass);
    }
}
