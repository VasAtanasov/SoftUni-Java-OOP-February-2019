import chainblock.*;
import org.junit.Assert;
import org.junit.Test;

public class Test05 {
    //Contains
    @Test
    public void Contains_OnEmptyChainblock_ShouldReturnFalse()
    {
        //Arrange
        Chainblock cb = new ChainblockImpl();
        //Act
        //Assert
        Assert.assertFalse(cb.contains(5));
        Assert.assertFalse(cb.contains(new TransactionImpl(3, TransactionStatus.Failed, "a", "b", 0.5)));
    }
}
