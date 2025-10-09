package model.expression;

public enum Precedence {
    LOWEST,
    SUM,        // + or -
    PRODUCT,    // * or /
    POWER,      // ** or ^
    GROUP;      // ()

    public final int value;
    
    Precedence() {
        this.value = ordinal();
    }
}
