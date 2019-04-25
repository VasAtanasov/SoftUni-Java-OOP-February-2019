import chainblock.*;
import org.junit.Test;

public class Test36 {
    @Test(expected = IllegalArgumentException.class)
    public void GetAllSendersWithTransactionStatus_ShoudlThrowAfterRemove()
    {
        //Arrange
        Chainblock cb = new ChainblockImpl();
        Transaction tx1 = new TransactionImpl(5, TransactionStatus.Successfull, "joro", "pesho", 1);
        Transaction tx2 = new TransactionImpl(6, TransactionStatus.Successfull, "joro", "pesho", 5.5);
        Transaction tx3 = new TransactionImpl(7, TransactionStatus.Successfull, "joro", "pesho", 5.5);
        Transaction tx4 = new TransactionImpl(12, TransactionStatus.Successfull, "joro", "pesho", 15.6);
        Transaction tx5 = new TransactionImpl(15, TransactionStatus.Failed, "joro", "pesho", 7.8);
        //Act
        cb.add(tx1);
        cb.add(tx3);
        cb.add(tx2);
        cb.add(tx4);
        cb.add(tx5);
        cb.removeTransactionById(5);
        cb.removeTransactionById(7);
        cb.removeTransactionById(6);
        cb.removeTransactionById(12);
        cb.removeTransactionById(15);
        //Assert
        cb.getAllSendersWithTransactionStatus(TransactionStatus.Failed);
    }

}
