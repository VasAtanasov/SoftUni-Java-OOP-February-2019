import chainblock.ChainblockImpl;
import chainblock.Chainblock;
import org.junit.Assert;
import org.junit.Test;

public class Test12 {
    @Test
    public void GetById_On_Empty_Chainblock_ShouldThrow()
    {
        //Arrange
        Chainblock cb = new ChainblockImpl();
        //Act
        //Assert
        boolean throwed = false;
        try{
            cb.getById(5);
        }catch(IllegalArgumentException ex){
            throwed = true;
        }
        Assert.assertTrue(throwed);
    }

}
