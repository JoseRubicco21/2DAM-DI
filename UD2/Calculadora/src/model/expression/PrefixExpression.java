package model.expression;

import model.token.Token;

public class PrefixExpression extends Expression {
    
    private Token token;
    private Expression right;
    private String operator;

    public PrefixExpression(Token token, String operator) {
        this.token = token;
        this.operator = operator;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "(" + token.literal + " " + (right != null ? right.toString() : "") + ")";
    }

    @Override
    public String TokenLiteral() {
        return token.literal;
    }
}
