import chainblock.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Test34 {

    //GetBySenderWithTransactionStatus
    @Test
    public void GetAllSendersWithTransactionStatus_ShouldWorkCorrectly()
    {
        //Arrange
        Chainblock cb = new ChainblockImpl();
        Transaction tx1 = new TransactionImpl(5, TransactionStatus.Successfull, "joro", "pesho", 1);
        Transaction tx2 = new TransactionImpl(6, TransactionStatus.Aborted, "joro", "pesho", 5.5);
        Transaction tx3 = new TransactionImpl(7, TransactionStatus.Successfull, "joro", "pesho", 5.5);
        Transaction tx4 = new TransactionImpl(12, TransactionStatus.Unauthorized, "boro", "pesho", 15.6);
        Transaction tx5 = new TransactionImpl(15, TransactionStatus.Unauthorized, "moro", "pesho", 7.8);
        String[] expected = new String[]
                {
                        "boro", "moro"
                };
        //Act
        cb.add(tx1);
        cb.add(tx3);
        cb.add(tx2);
        cb.add(tx4);
        cb.add(tx5);
        ArrayList<String> list = new ArrayList<String>();
        Iterable<String> res = cb.getAllSendersWithTransactionStatus(TransactionStatus.Unauthorized);
        for(String tx : res){
            list.add(tx);
        }
        String[] actual = new String[list.size()];
        for(int i = 0 ; i < list.size(); i++){
            actual[i] = list.get(i);
        }
        //Assert
        Assert.assertArrayEquals(expected, actual);
    }
}
