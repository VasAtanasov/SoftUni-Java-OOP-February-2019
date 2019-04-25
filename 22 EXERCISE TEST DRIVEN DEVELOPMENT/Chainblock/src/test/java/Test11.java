import chainblock.*;
import org.junit.Test;

public class Test11 {
    @Test(expected = IllegalArgumentException.class)
    public void GetById_On_NonExistingElement_ShouldThrow()
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
        cb.removeTransactionById(5);
        //Assert

        cb.getById(5);

    }
}
