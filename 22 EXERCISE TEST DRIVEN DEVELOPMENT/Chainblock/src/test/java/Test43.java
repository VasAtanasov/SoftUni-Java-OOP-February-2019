import chainblock.ChainblockImpl;
import chainblock.Chainblock;
import org.junit.Assert;
import org.junit.Test;

public class Test43 {
    @Test
    public void GetByReceiverAndAmountRange_ShouldThrow_On_EmptyCB()
    {
        //Arrange
        Chainblock cb = new ChainblockImpl();
        //Act
        //Assert
        boolean threw = false;
        try{
            cb.getByReceiverAndAmountRange("pesho", 0, 20).iterator().next();
        }catch(IllegalArgumentException ex){
            threw = true;
        }
        Assert.assertTrue(threw);
    }

}
