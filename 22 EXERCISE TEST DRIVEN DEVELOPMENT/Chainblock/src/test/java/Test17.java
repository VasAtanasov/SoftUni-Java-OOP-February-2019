import chainblock.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Test17 {
    //GetAllOrderedByAmountDescendingThenById
    @Test
    public void GetAllOrderedByAmountDescendingThenById_ShouldWorkCorrectly()
    {
        //Arrange
        Chainblock cb = new ChainblockImpl();
        Transaction tx1 = new TransactionImpl(5, TransactionStatus.Successfull, "joro", "pesho", 1);
        Transaction tx2 = new TransactionImpl(6, TransactionStatus.Successfull, "joro", "pesho", 5.5);
        Transaction tx3 = new TransactionImpl(7, TransactionStatus.Successfull, "joro", "pesho", 5.5);
        Transaction tx4 = new TransactionImpl(12, TransactionStatus.Successfull, "joro", "pesho", 15.6);
        Transaction tx5 = new TransactionImpl(15, TransactionStatus.Successfull, "joro", "pesho", 7.8);
        Transaction[] expected = new Transaction[]
                {
                        tx4,tx5,tx2,tx3,tx1
                };
        //Act
        cb.add(tx1);
        cb.add(tx3);
        cb.add(tx2);
        cb.add(tx4);
        cb.add(tx5);
        ArrayList<Transaction> list = new ArrayList<Transaction>();
        Iterable<Transaction> res = cb.getAllOrderedByAmountDescendingThenById();
        for(Transaction tx : res){
            list.add(tx);
        }
        Transaction[] actual = new Transaction[list.size()];
        for(int i = 0 ; i < list.size(); i++){
            actual[i] = list.get(i);
        }
        //Assert
        Assert.assertArrayEquals(expected, actual);
    }
}
