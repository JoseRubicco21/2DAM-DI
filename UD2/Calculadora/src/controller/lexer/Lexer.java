package controller.lexer;
import model.token.Token;
import model.token.TokenType;

public class Lexer {
    private String input;
    private int position;
    private int readPosition;
    private char ch;

    public Lexer(String input) {
        this.input = input;
        this.position = 0;
        this.readPosition = 0;
        readChar();
    }

    private void readChar() {
        if (readPosition >= input.length()) {
            ch = '\0';
        } else {
            ch = input.charAt(readPosition);
        }
        position = readPosition;
        readPosition++;
    }

    private char peekChar() {
        if (readPosition >= input.length()) {
            return '\0';
        } else {
            return input.charAt(readPosition);
        }
    }

    private void skipWhitespace() {
        while (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r') {
            readChar();
        }
    }

    private String readNumber() {
        int startPosition = position;
        while (Character.isDigit(ch)) {
            readChar();
        }
        return input.substring(startPosition, position);
    }

    public Token nextToken() {
        Token token;
        
        skipWhitespace();

        switch (ch) {
            case '+':
                token = new Token(TokenType.PLUS, String.valueOf(ch));
                break;
            case '-':
                token = new Token(TokenType.MINUS, String.valueOf(ch));
                break;
            case '*':
                token = new Token(TokenType.MULTIPLY, String.valueOf(ch));
                break;
            case '/':
                token = new Token(TokenType.DIVIDE, String.valueOf(ch));
                break;
            case '^':
                token = new Token(TokenType.POWER, String.valueOf(ch));
                break;
            case '(':
                token = new Token(TokenType.LPAREN, String.valueOf(ch));
                break;
            case ')':
                token = new Token(TokenType.RPAREN, String.valueOf(ch));
                break;
            case '\0':
                token = new Token(TokenType.EOF, "");
                break;
            default:
                if (Character.isDigit(ch)) {
                    String number = readNumber();
                    return new Token(TokenType.INT, number);
                } else {
                    token = new Token(TokenType.ILLEGAL, String.valueOf(ch));
                }
                break;
        }
        
        readChar();
        return token;
    }
}