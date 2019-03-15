import java.util.ArrayList;
import java.util.List;

public class StackOfStrings extends ArrayList {
    private List<String> data;

    public StackOfStrings() {
        this.data = new ArrayList<>();
    }

    public void push(String item) {
        this.data.add(item);
    }

    public String pop() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException("Stack is empty");
        }
        return this.data.remove(this.data.size() - 1);
    }

    public String peek() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException("Stack is empty");
        }
        return this.data.get(this.data.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return data.size() == 0;
    }
}
