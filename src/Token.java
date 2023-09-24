import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Token {
    public enum TokenType {
        NUMBER, PLUS,
        MINUS, MULTIPLY,
        DIVIDE, POWEROF,
        OPENBRACKETS, CLOSEDBRACKETS,
        SIN, COS,
        TAN;
    }

    private TokenType type;
    private String lexeme;

    public Token(TokenType type, String lexeme) {
        this.type = type;
        this.lexeme = lexeme;
    }

    public static boolean isOperator(Token token) {
        return (token.getType() == Token.TokenType.PLUS || token.getType() == Token.TokenType.MINUS
                || token.getType() == Token.TokenType.MULTIPLY || token.getType() == Token.TokenType.DIVIDE
                || token.getType() == Token.TokenType.POWEROF);
    }
    
    public boolean isOperator() {
        return (this.type == Token.TokenType.PLUS || this.type == Token.TokenType.MINUS
                || this.type == Token.TokenType.MULTIPLY || this.type == Token.TokenType.DIVIDE
                || this.type == Token.TokenType.POWEROF);
    }

    public static boolean hasHigherOrEqualPrecedence(Token token, Token toCompare) {
        Map<TokenType, Integer> operatorPrecedence = new HashMap<>();

        operatorPrecedence.put(TokenType.OPENBRACKETS, 0);
        operatorPrecedence.put(TokenType.CLOSEDBRACKETS, 0);
        operatorPrecedence.put(TokenType.PLUS, 1);
        operatorPrecedence.put(TokenType.MINUS, 1);
        operatorPrecedence.put(TokenType.MULTIPLY, 2);
        operatorPrecedence.put(TokenType.DIVIDE, 2);
        operatorPrecedence.put(TokenType.POWEROF, 3);

        return (operatorPrecedence.get(token.getType()) >= operatorPrecedence.get(toCompare.getType()));
    }

    public static void printTokenList(ArrayList<Token> tokens) {
        for (Token t : tokens) {
            System.out.println("{ " + t.type + ", " + t.lexeme + " }");
        }
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }
}

