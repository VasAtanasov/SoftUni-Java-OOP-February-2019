package Ex03DependencyInversion.factories;

import Ex03DependencyInversion.calculateStrategies.AdditionStrategy;
import Ex03DependencyInversion.calculateStrategies.DivisionStrategy;
import Ex03DependencyInversion.calculateStrategies.MultiplicationStrategy;
import Ex03DependencyInversion.calculateStrategies.SubtractionStrategy;
import Ex03DependencyInversion.interfaces.Strategy;

public final class StrategiesFactory {

    private StrategiesFactory(){}

    public static Strategy createStrategy(char operator) {
        switch (operator) {
            case '+':
                return new AdditionStrategy();
            case '-':
                return new SubtractionStrategy();
            case '*':
                return new MultiplicationStrategy();
            case '/':
                return new DivisionStrategy();
        }
        return null;
    }
}
