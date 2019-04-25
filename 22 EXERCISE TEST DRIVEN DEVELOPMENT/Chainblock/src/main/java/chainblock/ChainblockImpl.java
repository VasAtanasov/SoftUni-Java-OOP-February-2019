package chainblock;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock {

    private static final Comparator<Transaction> BY_AMOUNT_DESC =
            (a, b) -> Double.compare(b.getAmount(), a.getAmount());

    private static final Comparator<Transaction> BY_AMOUNT_DESC_BY_ID_ASC = (a, b) -> {
        int compare = Double.compare(b.getAmount(), a.getAmount());
        return compare != 0 ? compare : Integer.compare(a.getId(), b.getId());
    };

    private Map<Integer, Transaction> byId = new LinkedHashMap<>();

    private Map<TransactionStatus, List<Transaction>> byStatus = new HashMap<>() {{
        put(TransactionStatus.Successfull, new ArrayList<>());
        put(TransactionStatus.Failed, new ArrayList<>());
        put(TransactionStatus.Unauthorized, new ArrayList<>());
        put(TransactionStatus.Aborted, new ArrayList<>());
    }};

    private SortedSet<Transaction> byAmount = new TreeSet<>(BY_AMOUNT_DESC_BY_ID_ASC);


    @Override
    public int getCount() {
        return this.byId.size();
    }

    @Override
    public void add(Transaction tx) {
        if (this.byId.containsKey(tx.getId())) {
            return;
        }
        this.byId.putIfAbsent(tx.getId(), tx);
        this.byStatus.get(tx.getStatus()).add(tx);
        this.byAmount.add(tx);
    }

    @Override
    public boolean contains(Transaction tx) {
        return this.contains(tx.getId());
    }

    @Override
    public boolean contains(int id) {
        return this.byId.containsKey(id);
    }

    @Override
    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        Transaction transaction = this.getById(id);
        this.byStatus.get(transaction.getStatus()).remove(transaction);
        transaction.setStatus(newStatus);
        this.byStatus.get(newStatus).add(transaction);
    }

    @Override
    public void removeTransactionById(int id) {
        Transaction transaction = this.getById(id);
        this.byStatus.get(transaction.getStatus()).remove(transaction);
        this.byId.remove(id);
        this.byAmount.remove(transaction);
    }

    @Override
    public Transaction getById(int id) {
        Transaction transaction = this.byId.get(id);
        if (transaction == null) {
            throw new IllegalArgumentException();
        }
        return transaction;
    }

    @Override
    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> transactions = this.byStatus.get(status);
        if (transactions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        transactions.sort(BY_AMOUNT_DESC);
        return transactions;
    }

    @Override
    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        return this.getAllWithTransactionStatus(status, Transaction::getSender);
    }

    @Override
    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        return this.getAllWithTransactionStatus(status, Transaction::getReceiver);
    }

    private Iterable<String> getAllWithTransactionStatus(TransactionStatus status, Function<Transaction, String> action) {
        List<Transaction> transactions = this.byStatus.get(status);
        if (transactions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return transactions
                .stream()
                .sorted(BY_AMOUNT_DESC)
                .map(action)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return this.byAmount;
    }

    @Override
    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> transactions = this.byId.values()
                .stream()
                .filter(transaction -> transaction.getSender().equals(sender))
                .sorted(BY_AMOUNT_DESC)
                .collect(Collectors.toList());
        if (transactions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return transactions;
    }

    @Override
    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> transactions = this.byAmount
                .stream()
                .filter(transaction -> transaction.getReceiver().equals(receiver))
                .collect(Collectors.toList());
        if (transactions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return transactions;
    }

    @Override
    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return this.byStatus.get(status)
                .stream()
                .filter(transaction -> transaction.getAmount() <= amount)
                .sorted(BY_AMOUNT_DESC)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        List<Transaction> transactions = this.byId.values()
                .stream()
                .filter(transaction -> transaction.getSender().equals(sender) && transaction.getAmount() > amount)
                .sorted(BY_AMOUNT_DESC)
                .collect(Collectors.toList());

        if (transactions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return transactions;
    }

    @Override
    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        List<Transaction> transactions = this.byAmount
                .stream()
                .filter(t -> t.getReceiver().equals(receiver) && t.getAmount() >= lo && t.getAmount() < hi)
                .collect(Collectors.toList());
        if (transactions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return transactions;
    }

    @Override
    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return this.byId.values()
                .stream()
                .filter(t -> t.getAmount() >= lo && t.getAmount() <= hi)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Transaction> iterator() {
        return this.byId.values().iterator();
    }
}
