package model;

import java.time.LocalDateTime;

public class HistoryEntry {
    private String expression;
    private double result;
    private LocalDateTime timestamp;

    public HistoryEntry(String expression, double result) {
        this.expression = expression;
        this.result = result;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and setters
    public String getExpression() { return expression; }
    public double getResult() { return result; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return expression + " = " + result + " (" + timestamp.toString() + ")";
    }
}