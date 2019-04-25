import chainblock.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Test41 {
    @Test
    public void GetByRceiver_ShouldReturnCorrectRange_CorrectlyOrdered()
    {
        //Arrange
        Chainblock cb = new ChainblockImpl();
        Transaction tx1 = new TransactionImpl(2, TransactionStatus.Successfull, "joro", "pesho", 1);
        Transaction tx2 = new TransactionImpl(1, TransactionStatus.Successfull, "joro", "pesho", 1);
        Transaction tx3 = new TransactionImpl(4, TransactionStatus.Successfull, "joro", "pesho", 15.6);
        Transaction tx4 = new TransactionImpl(3, TransactionStatus.Successfull, "joro", "pesho", 15.6);
        Transaction tx5 = new TransactionImpl(8, TransactionStatus.Failed, "joro", "pesho", 17.8);
        Transaction[] expected = new Transaction[]
                {
                        tx5, tx4, tx3, tx2, tx1
                };
        //Act
        cb.add(tx1);
        cb.add(tx3);
        cb.add(tx2);
        cb.add(tx4);
        cb.add(tx5);
        //Assert
        ArrayList<Transaction> list = new ArrayList<Transaction>();
        Iterable<Transaction> res = cb.getByReceiverAndAmountRange("pesho", 0, 20);
        for(Transaction tx : res){
            list.add(tx);
        }
        Transaction[] actual = new Transaction[list.size()];
        for(int i = 0 ; i < list.size(); i++){
            actual[i] = list.get(i);
        }
        Assert.assertArrayEquals(expected, actual);
    }

}
