import chainblock.*;
import org.junit.Assert;
import org.junit.Test;

public class Test10 {
    //GetById
    @Test
    public void GetById_On_ExistingElement_ShouldWorkCorrectly()
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
        Assert.assertSame(tx1, cb.getById(5));
        Assert.assertNotSame(
                new TransactionImpl(53, TransactionStatus.Failed, "a", "b", 5),
                cb.getById(7)
        );
    }
}
