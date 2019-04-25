import chainblock.ChainblockImpl;
import chainblock.Chainblock;
import org.junit.Assert;
import org.junit.Test;

public class Test08 {
    @Test
    public void Count_Should_Be_0_On_EmptyCollection()
    {
        //Arrange
        Chainblock cb = new ChainblockImpl();
        //Act
        //Assert
        Assert.assertEquals(0, cb.getCount());
    }
}
