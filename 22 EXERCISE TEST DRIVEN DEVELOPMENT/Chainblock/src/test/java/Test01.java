import chainblock.*;
import org.junit.Assert;
import org.junit.Test;


public class Test01
{

    //addition
    @Test
    public void add_SingleElement_ShouldWorkCorrectly()
    {
        //Arrange
        Chainblock cb = new ChainblockImpl();
        Transaction tx = new TransactionImpl(5, TransactionStatus.Successfull, "joro", "pesho", 5);
        //Act
        cb.add(tx);

        //Assert
        for (Transaction transaction : cb)
        {
            Assert.assertSame(transaction, tx);
        }
    }

}