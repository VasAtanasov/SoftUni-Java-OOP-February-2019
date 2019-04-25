import chainblock.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Test21 {
    @Test
    public void GetByTransactionStatusAndMaximumAmount_ShouldReturnEmptyCollection()
    {
        //Arrange
        Chainblock cb = new ChainblockImpl();
        Transaction tx1 = new TransactionImpl(2, TransactionStatus.Successfull, "valq", "pesho", 14.8);
        Transaction tx2 = new TransactionImpl(1, TransactionStatus.Successfull, "valq", "pesho", 14.8);
        Transaction tx3 = new TransactionImpl(4, TransactionStatus.Successfull, "valq", "pesho", 15.6);
        Transaction tx4 = new TransactionImpl(3, TransactionStatus.Successfull, "valq", "pesho", 15.6);
        Transaction tx5 = new TransactionImpl(8, TransactionStatus.Failed, "valq", "pesho", 17.8);
        //Act
        cb.add(tx3);
        cb.add(tx2);
        cb.add(tx4);
        cb.add(tx5);
        //Assert
        ArrayList<Transaction> list = new ArrayList<Transaction>();
        Iterable<Transaction> res = cb.getByTransactionStatusAndMaximumAmount(TransactionStatus.Unauthorized,5);
        for(Transaction tx : res){
            list.add(tx);
        }
        Transaction[] actual = new Transaction[list.size()];
        for(int i = 0 ; i < list.size(); i++){
            actual[i] = list.get(i);
        }
        Assert.assertArrayEquals(new Transaction[0], actual);
        cb = new ChainblockImpl();
        list = new ArrayList<Transaction>();
        res = cb.getByTransactionStatusAndMaximumAmount(TransactionStatus.Unauthorized,1);
        for(Transaction tx : res){
            list.add(tx);
        }
        actual = new Transaction[list.size()];
        for(int i = 0 ; i < list.size(); i++){
            actual[i] = list.get(i);
        }
        Assert.assertArrayEquals(new Transaction[0], actual);
    }
}
