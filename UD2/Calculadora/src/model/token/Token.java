package model.token;

public class Token {
    
    public TokenType tokenType;
    public String literal;
    

    public Token(TokenType tokenType, String literal) {
        this.tokenType = tokenType;
        this.literal = literal;
    }

    public TokenType getTokenType() {
        return tokenType;
    }
    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }
    public String getLiteral() {
        return literal;
    }
    public void setLiteral(String literal) {
        this.literal = literal;
    }
    
    
}