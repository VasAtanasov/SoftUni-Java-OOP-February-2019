import chainblock.ChainblockImpl;
import chainblock.Chainblock;
import org.junit.Test;

public class Test46 {
    @Test(expected = IllegalArgumentException.class)
    public void GetByReceiver_ShouldThrow_On_EmptyCB()
    {
        //Arrange
        Chainblock cb = new ChainblockImpl();
        //Act
        //Assert
        cb.getByReceiverOrderedByAmountThenById("pesho");
    }

    //GetBySenderAndMinimumAmountDescending
}
