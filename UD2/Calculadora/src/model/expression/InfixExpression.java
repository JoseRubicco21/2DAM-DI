package model.expression;

import model.token.Token;

public class InfixExpression extends Expression {

    private Token leftToken;
    private Expression left;
    private Token operatorToken;
    private Expression right;

    public InfixExpression(Token leftToken, Expression left, Token operatorToken, Expression right) {
        this.leftToken = leftToken;
        this.left = left;
        this.operatorToken = operatorToken;
        this.right = right;
    }

    public Token getLeftToken() {
        return leftToken;
    }

    public void setLeftToken(Token leftToken) {
        this.leftToken = leftToken;
    }

    public Expression getLeft() {
        return left;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public Token getOperatorToken() {
        return operatorToken;
    }

    public void setOperatorToken(Token operatorToken) {
        this.operatorToken = operatorToken;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + (left != null ? left.toString() : "") + " " + operatorToken.literal + " " + (right != null ? right.toString() : "") + ")";
    }

    @Override
    public String TokenLiteral() {
        return operatorToken.literal;
    }
}
