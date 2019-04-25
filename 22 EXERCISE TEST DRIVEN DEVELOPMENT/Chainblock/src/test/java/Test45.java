import chainblock.*;
import org.junit.Test;

public class Test45 {
    @Test(expected = IllegalArgumentException.class)
    public void GetByReceiver_On_NonExisting_Receiver_ShouldThrow()
    {
        //Arrange
        Chainblock cb = new ChainblockImpl();
        Transaction tx1 = new TransactionImpl(2, TransactionStatus.Successfull, "joro", "pesho", 1);
        Transaction tx2 = new TransactionImpl(1, TransactionStatus.Successfull, "joro", "mesho", 1);
        Transaction tx3 = new TransactionImpl(4, TransactionStatus.Successfull, "joro", "kalin", 15.6);
        Transaction tx4 = new TransactionImpl(3, TransactionStatus.Successfull, "joro", "pesho", 15.6);
        Transaction tx5 = new TransactionImpl(8, TransactionStatus.Failed, "joro", "barko", 17.8);

        //Act
        cb.add(tx1);
        cb.add(tx3);
        cb.add(tx2);
        cb.add(tx4);
        cb.add(tx5);
        //Assert

        cb.getByReceiverOrderedByAmountThenById("mecho");

    }
}
