import chainblock.*;
import org.junit.Test;

public class Test24 {
    @Test(expected = IllegalArgumentException.class)
    public void GetByNonExistingTransactionStatus_ShouldThrow()
    {
        //Arrange
        Chainblock cb = new ChainblockImpl();
        Transaction tx1 = new TransactionImpl(2, TransactionStatus.Successfull, "valq", "pesho", 14.8);
        Transaction tx2 = new TransactionImpl(1, TransactionStatus.Successfull, "valq", "pesho", 14.8);
        Transaction tx3 = new TransactionImpl(4, TransactionStatus.Successfull, "valq", "pesho", 15.6);
        Transaction tx4 = new TransactionImpl(3, TransactionStatus.Successfull, "valq", "pesho", 15.6);
        Transaction tx5 = new TransactionImpl(8, TransactionStatus.Failed, "valq", "pesho", 17.8);
        //Act
        cb.add(tx1);
        cb.add(tx3);
        cb.add(tx2);
        cb.add(tx4);
        cb.add(tx5);
        //Assert
        cb.getByTransactionStatus(TransactionStatus.Unauthorized);
    }

}
