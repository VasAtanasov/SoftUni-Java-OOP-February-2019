import java.util.ArrayDeque;
import java.util.Deque;

public class InputInterpreter {
    private CalculationEngine engine;
    private Deque<Integer> memory;

    public InputInterpreter(CalculationEngine engine) {
        this.engine = engine;
        this.memory = new ArrayDeque<>();
    }

    public boolean interpret(String input) {
        if ("ms".equals(input)) {
            this.memory.addLast(engine.getCurrentResult());
            return true;
        }

        if ("mr".equals(input)) {
            engine.pushNumber(this.memory.removeLast());
            return true;
        }

        try {
            engine.pushNumber(Integer.parseInt(input));
        } catch (Exception ex) {
            engine.pushOperation(this.getOperation(input));
        }

        return true;
    }

    public Operation getOperation(String operation) {
        if (operation.equals("*")) {
            return new MultiplicationOperation();
        }

        if (operation.equals("/")) {
            return new DivisionOperation();
        }
        return null;
    }
}
