package Ex03DependencyInversion.calculateStrategies;

import Ex03DependencyInversion.interfaces.Strategy;

public class DivisionStrategy implements Strategy {
    @Override
    public int calculate(int firstOperand, int secondOperand) {
        return firstOperand / secondOperand;
    }
}
