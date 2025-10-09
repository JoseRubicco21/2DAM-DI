package model.expression;

import model.token.Token;

public class IntegerExpression extends Expression {
    
    private Token token;
    private long value;

    public IntegerExpression(Token token) {
        this.token = token;
        this.value = Long.parseLong(token.literal);
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
        this.value = Long.parseLong(token.literal);
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public String TokenLiteral() {
        return token.literal;
    }
}
