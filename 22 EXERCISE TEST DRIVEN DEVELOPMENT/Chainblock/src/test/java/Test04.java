import chainblock.*;
import org.junit.Assert;
import org.junit.Test;

public class Test04 {
    @Test
    public void add_MultipleElements_CB_ShouldContainThemById()
    {
        //Arrange
        Chainblock cb = new ChainblockImpl();
        Transaction tx1 = new TransactionImpl(5, TransactionStatus.Successfull, "joro", "pesho", 5);
        Transaction tx2 = new TransactionImpl(6, TransactionStatus.Successfull, "joro", "pesho", 5);
        Transaction tx3 = new TransactionImpl(7, TransactionStatus.Successfull, "joro", "pesho", 5);
        //Act
        cb.add(tx1);
        cb.add(tx2);
        cb.add(tx3);
        //Assert
        Assert.assertTrue(cb.contains(tx1.getId()));
        Assert.assertTrue(cb.contains(tx2.getId()));
        Assert.assertTrue(cb.contains(tx3.getId()));
    }

}
