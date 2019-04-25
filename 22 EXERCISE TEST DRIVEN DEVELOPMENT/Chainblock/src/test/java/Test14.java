import chainblock.*;
import org.junit.Assert;
import org.junit.Test;

public class Test14 {
    @Test
    public void ChangeTransactionStatus_OnMultipleTransactions_ShouldWorkCorrectly()
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
        cb.changeTransactionStatus(7, TransactionStatus.Unauthorized);
        cb.changeTransactionStatus(5, TransactionStatus.Aborted);
        cb.changeTransactionStatus(6, TransactionStatus.Successfull);
        //Assert
        Assert.assertEquals(3, cb.getCount());
        Assert.assertEquals(tx1.getStatus(), TransactionStatus.Aborted);
        Assert.assertEquals(tx3.getStatus(), TransactionStatus.Unauthorized);
        Assert.assertEquals(tx2.getStatus(), TransactionStatus.Successfull);
    }
}
