package chainblock;

public interface Transaction extends Comparable<Transaction> {

    int getId();

    void setId(int id);

    TransactionStatus getStatus();

    void setStatus(TransactionStatus status);

    String getSender();

    void setSender(String sender);

    String getReceiver();

    void setReceiver(String receiver);

    double getAmount();

    void setAmount(double amount);
}
