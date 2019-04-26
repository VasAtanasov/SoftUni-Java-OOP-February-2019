package Ex03DependencyInversion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        PrimitiveCalculator calculator = new PrimitiveCalculator();

        while (true) {
            String[] tokens = reader.readLine().split("\\s+");
            if ("End".equalsIgnoreCase(tokens[0])) {
                break;
            }

            if("mode".equals(tokens[0])){
                calculator.changeStrategy(tokens[1].charAt(0));
            }else{
                int firstOperand = Integer.parseInt(tokens[0]);
                int secondOperand = Integer.parseInt(tokens[1]);
                System.out.println(calculator.performCalculation(firstOperand, secondOperand));
            }
        }
    }
}
