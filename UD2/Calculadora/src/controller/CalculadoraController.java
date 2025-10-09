package controller;

import java.util.Map;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import controller.lexer.Lexer;
import controller.parser.Parser;
import controller.evaluator.Evaluator;
import model.expression.Expression;
import model.HistoryEntry;

public class CalculadoraController {

    private Stack<HistoryEntry> history;
    private Evaluator evaluator;

    public CalculadoraController() {
        this.evaluator = new Evaluator();
        this.history = new Stack<>();
    }

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return a / b;
    }

    public double power(double a, double b) {
        return Math.pow(a, b);
    }

    public double parseOperation(String operation) {
        Lexer lexer = new Lexer(operation);
        Parser parser = new Parser(lexer);
        Expression expr = parser.Parse();
        
        if (expr != null) {
            System.out.println("Parsed expression: " + expr.toString());
            double result = evaluator.evaluate(expr);
            
            // Add to history
            history.push(new HistoryEntry(operation, result));
            
            return result;
        }
        
        throw new IllegalArgumentException("Failed to parse expression: " + operation);
    }

    // History methods
    public List<HistoryEntry> getHistory() {
        return new ArrayList<>(history);
    }

    public HistoryEntry getLastEntry() {
        if (history.isEmpty()) {
            throw new IllegalStateException("No history available");
        }
        return history.peek();
    }

    public double getLastResult() {
        return getLastEntry().getResult();
    }

    public void clearHistory() {
        history.clear();
    }

    public HistoryEntry undoLast() {
        if (history.isEmpty()) {
            throw new IllegalStateException("No history to undo");
        }
        return history.pop();
    }

    // Getter for the evaluator if needed
    public Evaluator getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }
}
