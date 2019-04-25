package chainblock;

import java.util.Objects;

public class TransactionImpl implements Transaction {

    private int id;
    private TransactionStatus status;
    private String sender;
    private String receiver;
    private double amount;

    public TransactionImpl(int id, TransactionStatus status, String sender, String receiver, double amount) {
        this.id = id;
        this.status = status;
        this.receiver = receiver;
        this.sender = sender;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public int compareTo(Transaction o) {
        return Integer.compare(this.getId(), o.getId());
    }
}
