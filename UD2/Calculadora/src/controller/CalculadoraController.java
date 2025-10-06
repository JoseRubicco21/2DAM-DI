package controller;

public class CalculadoraController {


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

}
