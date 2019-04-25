import chainblock.*;
import org.junit.Assert;
import org.junit.Test;

public class Test02 {
    @Test
    public void add_SingleElement_ShouldIncreaseCountTo1()
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

        Assert.assertEquals(1, cb.getCount());
    }
}
