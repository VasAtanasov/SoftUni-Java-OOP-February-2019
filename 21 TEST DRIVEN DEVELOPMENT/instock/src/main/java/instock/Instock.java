package instock;

import java.util.*;
import java.util.stream.Collectors;

public class Instock implements ProductStock {

    private Map<String, Product> byLabel;
    private List<Product> byInsert;

    public Instock() {
        this.byLabel = new TreeMap<>();
        this.byInsert = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.byInsert.size();
    }

    @Override
    public boolean contains(Product product) {
        return this.byLabel.containsKey(product.getLabel());
    }

    @Override
    public void add(Product product) {
        if (this.byLabel.containsKey(product.getLabel())) {
            return;
        }
        this.byLabel.putIfAbsent(product.getLabel(), product);
        this.byInsert.add(product);
    }

    @Override
    public void changeQuantity(String label, int quantity) {
        Product product = this.findByLabel(label);
        product.setQuantity(quantity);
        this.byInsert.remove(product);
        this.byLabel.remove(product.getLabel());
        this.add(product);
    }

    @Override
    public Product find(int index) {
        if (index < 0 || index >= this.getCount()) {
            throw new IndexOutOfBoundsException();
        }
        return this.byInsert.get(index);
    }

    @Override
    public Product findByLabel(String label) {
        Product product = this.byLabel.get(label);
        if (product == null) {
            throw new IllegalArgumentException();
        }
        return product;
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        if (this.getCount() < count) {
            throw new IllegalArgumentException();
        }
        return this.byLabel.values().stream().limit(count).collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        return this.byInsert.stream()
                .filter(product -> product.getPrice() > lo && product.getPrice() <= hi)
                .sorted((a, b) -> Double.compare(b.getPrice(), a.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return this.byInsert.stream()
                .filter(product -> product.getPrice() == price)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        if (this.getCount() < count) {
            throw new IllegalArgumentException();
        }
        return this.byInsert.stream()
                .sorted((a, b) -> Double.compare(b.getPrice(), a.getPrice()))
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return this.byInsert.stream()
                .filter(product -> product.getQuantity() == quantity)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {
        return this.byInsert.iterator();
    }
}
