package p03_IteratorTest;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListIterator {
    private List<String> elements;
    private int currentIndex;

    public ListIterator(List<String> elements) throws OperationNotSupportedException {
        if (elements == null) {
            throw new OperationNotSupportedException();
        }

        this.setCollection(elements);
        this.currentIndex = 0;
    }

    public ListIterator() {
        this.elements = new ArrayList<>();
    }

    public ListIterator(String... elements) throws OperationNotSupportedException {
        if (elements == null) {
            throw new OperationNotSupportedException();
        }
        this.elements = Arrays.asList(elements);
    }

    private void setCollection(List<String> collection) throws OperationNotSupportedException {
        if (collection == null) {
            throw new OperationNotSupportedException();
        }
        this.elements = collection;
    }

    public boolean move() {
        if (hasNext()) {
            this.currentIndex++;
            return true;
        }

        return false;
    }

    public boolean hasNext() {
        if (currentIndex < elements.size() - 1) {
            return true;
        }

        return false;
    }

    public String print() {
        if (this.elements.size() == 0) {
            throw new IllegalStateException("Invalid Operation!");
        }

        return this.elements.get(this.currentIndex);
    }
}
