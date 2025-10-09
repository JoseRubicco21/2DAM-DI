package controller.parser;

import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;
import controller.lexer.Lexer;
import model.token.Token;
import model.token.TokenType;
import model.expression.Precedence;
import model.expression.Expression;
import model.expression.IntegerExpression;
import model.expression.PrefixExpression;
import model.expression.InfixExpression;

public class Parser {
    
    private Lexer lexer;
    private Token currentToken;
    private Token peekToken;
    
    private static final Map<TokenType, Precedence> precedences = Map.of(
        TokenType.PLUS, Precedence.SUM,
        TokenType.MINUS, Precedence.SUM,
        TokenType.MULTIPLY, Precedence.PRODUCT,
        TokenType.DIVIDE, Precedence.PRODUCT,
        TokenType.POWER, Precedence.POWER,
        TokenType.LPAREN, Precedence.GROUP
    );

    private Map<TokenType, Function<Expression, Expression>> infixParseFns;
    private Map<TokenType, Function<Void, Expression>> prefixParseFns;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        
        // Initialize prefix parse functions
        prefixParseFns = new HashMap<>();
        prefixParseFns.put(TokenType.INT, (v) -> parseIntegerExpression());
        prefixParseFns.put(TokenType.MINUS, (v) -> parsePrefixExpression());
        prefixParseFns.put(TokenType.PLUS, (v) -> parsePrefixExpression());
        prefixParseFns.put(TokenType.LPAREN, (v) -> parseGroupedExpression());
        
        // Initialize infix parse functions
        infixParseFns = new HashMap<>();
        infixParseFns.put(TokenType.PLUS, this::parseInfixExpression);
        infixParseFns.put(TokenType.MINUS, this::parseInfixExpression);
        infixParseFns.put(TokenType.MULTIPLY, this::parseInfixExpression);
        infixParseFns.put(TokenType.DIVIDE, this::parseInfixExpression);
        infixParseFns.put(TokenType.POWER, this::parseInfixExpression);
        
        // Read two tokens, so currentToken and peekToken are both set
        nextToken();
        nextToken();
    }

    private void nextToken() {
        currentToken = peekToken;
        peekToken = lexer.nextToken();
    }

    public Expression Parse() {
        return parseExpression(Precedence.LOWEST);
    }

    public Expression parseExpression(Precedence precedence) {
        Function<Void, Expression> prefix = prefixParseFns.get(currentToken.tokenType);
        if (prefix == null) {
            noPrefixParseFnError(currentToken.tokenType);
            return null;
        }
        
        Expression leftExp = prefix.apply(null);
        
        while (!peekTokenIs(TokenType.EOF) && precedence.value < peekPrecedence().value) {
            Function<Expression, Expression> infix = infixParseFns.get(peekToken.tokenType);
            if (infix == null) {
                return leftExp;
            }
            
            nextToken();
            leftExp = infix.apply(leftExp);
        }
        
        return leftExp;
    }

    private Expression parseIntegerExpression() {
        return new IntegerExpression(currentToken);
    }

    private Expression parsePrefixExpression() {
        Token token = currentToken;
        String operator = currentToken.literal;
        
        nextToken();
        
        Expression right = parseExpression(Precedence.GROUP);
        
        PrefixExpression prefixExp = new PrefixExpression(token, operator);
        prefixExp.setRight(right);
        
        return prefixExp;
    }

    private Expression parseInfixExpression(Expression left) {
        Token operatorToken = currentToken;
        String operator = currentToken.literal;
        Precedence precedence = currentPrecedence();
        
        nextToken();
        
        Expression right = parseExpression(precedence);
        
        // Use your existing InfixExpression constructor
        InfixExpression infixExp = new InfixExpression(null, left, operatorToken, right);
        
        return infixExp;
    }

    private Expression parseGroupedExpression() {
        nextToken();
        
        Expression exp = parseExpression(Precedence.LOWEST);
        
        if (!expectPeek(TokenType.RPAREN)) {
            return null;
        }
        
        return exp;
    }

    // Helper methods
    private boolean currentTokenIs(TokenType tokenType) {
        return currentToken.tokenType == tokenType;
    }

    private boolean peekTokenIs(TokenType tokenType) {
        return peekToken.tokenType == tokenType;
    }

    private boolean expectPeek(TokenType tokenType) {
        if (peekTokenIs(tokenType)) {
            nextToken();
            return true;
        } else {
            peekError(tokenType);
            return false;
        }
    }

    private Precedence peekPrecedence() {
        Precedence prec = precedences.get(peekToken.tokenType);
        return prec != null ? prec : Precedence.LOWEST;
    }

    private Precedence currentPrecedence() {
        Precedence prec = precedences.get(currentToken.tokenType);
        return prec != null ? prec : Precedence.LOWEST;
    }

    // Error handling methods
    private void noPrefixParseFnError(TokenType tokenType) {
        System.err.println("No prefix parse function for " + tokenType + " found");
    }

    private void peekError(TokenType tokenType) {
        System.err.println("Expected next token to be " + tokenType + ", got " + peekToken.tokenType + " instead");
    }

    // Getters
    public Token getCurrentToken() {
        return currentToken;
    }

    public Token getPeekToken() {
        return peekToken;
    }
}
