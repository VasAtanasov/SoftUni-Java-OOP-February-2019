import chainblock.ChainblockImpl;
import chainblock.Chainblock;
import chainblock.TransactionStatus;
import org.junit.Assert;
import org.junit.Test;

public class Test18 {
    @Test
    public void ChangeTransactionStatus_On_NonExistingTranasction_ShouldThrow()
    {
        //Arrange
        Chainblock cb = new ChainblockImpl();
        //Act
        //Assert
        boolean threw = false;
        try{
            cb.changeTransactionStatus(6, TransactionStatus.Failed);
        }catch(IllegalArgumentException ex){
            threw = true;
        }
        Assert.assertTrue(threw);
    }

}
