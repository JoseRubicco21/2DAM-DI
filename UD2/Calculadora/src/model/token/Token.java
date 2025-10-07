package model.token;

public class Token {
    
    public Token tokenType;
    public String literal;
    
    public Token(Token tokenType, String literal) {
        this.tokenType = tokenType;
        this.literal = literal;
    }

    public Token getTokenType() {
        return tokenType;
    }

    public void setTokenType(Token tokenType) {
        this.tokenType = tokenType;
    }

    public String getLiteral() {
        return literal;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return String.format("[TOKEN: %s], [LITERAL: %s]", tokenType, literal);
    }
}