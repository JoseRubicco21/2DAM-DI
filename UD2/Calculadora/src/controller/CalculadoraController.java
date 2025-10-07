package controller;

import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

public class CalculadoraController {

    private Stack<Map<String, Integer>> history;
    private static String operandPattern = ("\\d+(?:\\.\\d+)?");
    private static String operatorPattern = ("[+\\-*/]");

    public double add(int a, int b) {
        return a + b;
    }

    public double subtract(int a, int b) {
        return a - b;
    }

    public double multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return (double) a / b;
    }

    public double power(int a, int b) {
        return Math.pow(a, b);
    }

    public void parseOperation(String operation){
       String[] operands = operation.split(operandPattern);
       String[] operators = operation.split(operatorPattern);

         for (String op : operands) {
              System.out.println(op);
         }

            for (String op : operators) {
                System.out.println(op);
            }
    }


}
