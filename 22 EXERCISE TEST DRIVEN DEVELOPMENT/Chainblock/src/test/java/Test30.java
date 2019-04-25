import chainblock.ChainblockImpl;
import chainblock.Chainblock;
import org.junit.Assert;
import org.junit.Test;

public class Test30 {
    @Test(expected = IllegalArgumentException.class)
    public void GetBySenderAndMinimumAmountDescending_ShouldThrowOnEmpty_CB() {
        //Arrange
        Chainblock cb = new ChainblockImpl();
        //Act
        //Assert
        boolean threw = false;
        cb.getBySenderAndMinimumAmountDescending("pesho", 5);
        Assert.assertTrue(threw);
    }
}
