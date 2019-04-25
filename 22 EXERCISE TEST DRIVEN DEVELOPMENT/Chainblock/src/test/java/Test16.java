import chainblock.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Test16 {
    @Test
    public void GetAllOrderedByAmountDescendingThenById_ShouldWorkCorrectlyAfterRemove()
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
                        tx5,tx3,tx1
                };
        //Act
        cb.add(tx1);
        cb.add(tx3);
        cb.add(tx2);
        cb.add(tx4);
        cb.add(tx5);
        ArrayList<Transaction> result = new ArrayList<Transaction>();
        Iterable<Transaction> res = cb.getAllOrderedByAmountDescendingThenById();
        for(Transaction tx : res){
            result.add(tx);
        }
        Assert.assertEquals(5, result.size());
        cb.removeTransactionById(tx4.getId());
        cb.removeTransactionById(tx2.getId());

        result = new ArrayList<Transaction>();
        res = cb.getAllOrderedByAmountDescendingThenById();
        for(Transaction tx : res){
            result.add(tx);
        }
        Transaction[] actual = new Transaction[result.size()];
        for(int i = 0 ; i < result.size(); i++){
            actual[i] = result.get(i);
        }
        //Assert
        Assert.assertArrayEquals(expected, actual);
    }

}
