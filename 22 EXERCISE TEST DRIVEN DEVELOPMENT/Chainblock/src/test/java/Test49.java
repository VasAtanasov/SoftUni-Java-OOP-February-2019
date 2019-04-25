import chainblock.ChainblockImpl;
import chainblock.Chainblock;
import chainblock.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Test49 {

    @Test
    public void GetAllInAmountRange_ShouldReturnEmptyEnumeration_On_EmptyCB()
    {
        //Arrange
        Chainblock cb = new ChainblockImpl();
        //Act
        ArrayList<Transaction> list = new ArrayList<Transaction>();
        Iterable<Transaction> res = cb.getAllInAmountRange(7.7, 7.75);
        for(Transaction tx : res){
            list.add(tx);
        }
        Transaction[] actual = new Transaction[list.size()];
        for(int i = 0 ; i < list.size(); i++){
            actual[i] = list.get(i);
        }
        //Assert
        Assert.assertArrayEquals(new Transaction[0], actual);
    }
}
