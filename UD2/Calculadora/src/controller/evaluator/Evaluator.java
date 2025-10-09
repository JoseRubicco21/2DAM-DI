package controller.evaluator;

import model.expression.Expression;
import model.expression.IntegerExpression;
import model.expression.InfixExpression;
import model.expression.PrefixExpression;

public class Evaluator {

    public double evaluate(Expression expr) {
        if (expr instanceof IntegerExpression) {
            return evaluateIntegerExpression((IntegerExpression) expr);
        } else if (expr instanceof InfixExpression) {
            return evaluateInfixExpression((InfixExpression) expr);
        } else if (expr instanceof PrefixExpression) {
            return evaluatePrefixExpression((PrefixExpression) expr);
        }
        
        throw new IllegalArgumentException("Unknown expression type: " + expr.getClass().getSimpleName());
    }

    private double evaluateIntegerExpression(IntegerExpression expr) {
        return expr.getValue();
    }

    private double evaluateInfixExpression(InfixExpression expr) {
        double left = evaluate(expr.getLeft());
        double right = evaluate(expr.getRight());
        
        // Fixed: Get the operator token, not the left token
        String operator = expr.getOperatorToken().literal;
        
        switch (operator) {
            case "+":
                return add(left, right);
            case "-":
                return subtract(left, right);
            case "*":
                return multiply(left, right);
            case "/":
                return divide(left, right);
            case "^":
            case "**":
                return power(left, right);
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    private double evaluatePrefixExpression(PrefixExpression expr) {
        double right = evaluate(expr.getRight());
        
        String operator = expr.getOperator();
        
        switch (operator) {
            case "-":
                return -right;
            case "+":
                return right;
            default:
                throw new IllegalArgumentException("Unknown prefix operator: " + operator);
        }
    }

    // Mathematical operations
    private double add(double a, double b) {
        return a + b;
    }

    private double subtract(double a, double b) {
        return a - b;
    }

    private double multiply(double a, double b) {
        return a * b;
    }

    private double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return a / b;
    }

    private double power(double a, double b) {
        return Math.pow(a, b);
    }
}