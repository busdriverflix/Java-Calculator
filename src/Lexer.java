import java.util.ArrayList;

class Lexer {
    private final String expression;

    public Lexer(String expression) {
        this.expression = expression;
    }

    public static boolean IsNumeric(char c) {
        return (c >= '0' && c <= '9' || c == ',');
    }

    public static boolean IsOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '^');
    }

    public ArrayList<Token> Tokenize() {
        ArrayList<Token> tokens = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            char curChar = expression.charAt(i);
            switch (curChar) {
                case ' ', '\n', '\t', '\r' -> {
                }
                case '(' -> {
                    tokens.add(new Token(Token.TokenType.OPENBRACKETS, "("));
                }
                case ')' -> {
                    tokens.add(new Token(Token.TokenType.CLOSEDBRACKETS, ")"));
                }
                case '+' -> {
                    tokens.add(new Token(Token.TokenType.PLUS, "+"));
                }
                case '–', '-' -> {
                    tokens.add(new Token(Token.TokenType.MINUS, "-"));
                }
                case '×', '*' -> {
                    tokens.add(new Token(Token.TokenType.MULTIPLY, "*"));
                }
                case '÷' -> {
                    tokens.add(new Token(Token.TokenType.DIVIDE, "/"));
                }
                case '^' -> {
                    tokens.add(new Token(Token.TokenType.POWEROF, "^"));
                }
                case 'π' -> {
                    tokens.add(new Token(Token.TokenType.NUMBER, String.valueOf(Math.PI)));
                }
                default -> {
                    if (IsNumeric(curChar)) {
                        String number = "";
                        while (i < expression.length() && IsNumeric(expression.charAt(i))) {
                            number += expression.charAt(i);
                            i++;
                        }

                        if (number.contains(",")) {
                            tokens.add(new Token(Token.TokenType.NUMBER, number.replace(",", ".")));
                        }else {
                            tokens.add(new Token(Token.TokenType.NUMBER, number));
                        }
                        i--;
                    } else {
                        System.out.println("Unjültijet Zeichen du dummer Spasti");
                    }
                }
            }
        }

        return tokens;
    }
}
