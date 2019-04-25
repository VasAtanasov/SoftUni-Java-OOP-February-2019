import chainblock.*;
import org.junit.Assert;
import org.junit.Test;

public class Test13 {
    //ChangeTxStatus
    @Test
    public void ChangeTransactionStatus_ShouldWorkCorrectly_On_ExistingTX()
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
        cb.changeTransactionStatus(5, TransactionStatus.Aborted);
        //Assert
        Assert.assertEquals(TransactionStatus.Aborted, tx1.getStatus());
        Assert.assertEquals(3, cb.getCount());
    }
}
