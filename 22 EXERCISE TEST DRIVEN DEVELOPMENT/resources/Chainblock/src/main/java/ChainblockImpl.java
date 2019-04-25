import java.util.Iterator;

public class ChainblockImpl implements Chainblock{
    public int getCount() {
        return 0;
    }

    public void add(Transaction transaction) {

    }

    public boolean contains(Transaction transaction) {
        return false;
    }

    public boolean contains(int id) {
        return false;
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {

    }

    public void removeTransactionById(int id) {

    }

    public Transaction getById(int id) {
        return null;
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        return null;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        return null;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        return null;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return null;
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        return null;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        return null;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return null;
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        return null;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        return null;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return null;
    }

    public Iterator<Transaction> iterator() {
        return null;
    }
}
