package instock;

/**
 * The <h3>IProductStock</h3> interface provides a set of methods which
 * should be implemented by your data structure. </para>
 * <p>All of the below-written methods will be tested so, please,
 * do not modify them in any way. You can add methods to the concrete class though.</p>
 */
public interface ProductStock extends Iterable<Product> {

    //Properties
    int getCount();

    //Validation
    boolean contains(Product product);

    //Modification
    void add(Product product);

    void changeQuantity(String product, int quantity);

    //Retrieval
    Product find(int index);

    Product findByLabel(String label);

    Iterable<Product> findFirstByAlphabeticalOrder(int count);

    //Querying
    Iterable<Product> findAllInRange(double lo, double hi);

    Iterable<Product> findAllByPrice(double price);

    Iterable<Product> findFirstMostExpensiveProducts(int count);

    Iterable<Product> findAllByQuantity(int quantity);

}
