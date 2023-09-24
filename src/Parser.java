import java.util.ArrayList;
import java.util.Stack;

class Parser {
    public static double solveSimpleExpression(double operand1, double operand2, Token.TokenType type) {
        switch (type) {
            case PLUS -> {
                return operand1 + operand2;
            }
            case MINUS -> {
                return operand1 - operand2;
            }
            case MULTIPLY -> {
                return operand1 * operand2;
            }
            case DIVIDE -> {
                if (operand2 == 0)
                    System.out.println("Unjültije Operation, Division durch Null");

                return operand1 / operand2;
            }
            case POWEROF -> {
                return Math.pow(operand1, operand2);
            }
            default -> {
                System.out.println("Unjültijer Operator | Operator unbekannt");
                return Double.NaN;
            }
        }
    }

    public static Token Parse(ArrayList<Token> tokensRPN) {
        Stack<Token> operandStack = new Stack<>();

        for (Token t : tokensRPN) {
            if (t.getType() == Token.TokenType.NUMBER) {
                operandStack.push(t);
            }
            else if (t.isOperator()) {
                double operand2 = Double.parseDouble(operandStack.pop().getLexeme());
                double operand1 = Double.parseDouble(operandStack.pop().getLexeme());

                double result = solveSimpleExpression(operand1, operand2, t.getType());
                operandStack.push(new Token(Token.TokenType.NUMBER, String.valueOf(result)));
            }
        }

        return operandStack.pop();
    }

    public static ArrayList<Token> toRPN(ArrayList<Token> tokens) {
        ArrayList<Token> tokensRPN = new ArrayList<>();
        Stack<Token> operatorStack = new Stack<>();

        for (Token t : tokens) {
            if (t.getType() == Token.TokenType.NUMBER) {
                tokensRPN.add(t);
            }
            else if (t.isOperator()) {
                if (operatorStack.isEmpty() || !Token.hasHigherOrEqualPrecedence(operatorStack.peek(), t)) {
                    operatorStack.push(t);
                } else {
                    while (!operatorStack.isEmpty()) {
                        tokensRPN.add(operatorStack.pop());
                    }
                    operatorStack.push(t);
                }
            }
            else if (t.getType() == Token.TokenType.OPENBRACKETS) {
                operatorStack.push(t);
            }
            else if (t.getType() == Token.TokenType.CLOSEDBRACKETS) {
                while (!operatorStack.isEmpty()) {
                    if (operatorStack.peek().getType() == Token.TokenType.OPENBRACKETS) {
                        break;
                    }

                    tokensRPN.add(operatorStack.pop());
                }
                operatorStack.pop();
            }
        }

        while(!operatorStack.isEmpty()) {
            tokensRPN.add(operatorStack.pop());
        }

        return tokensRPN;
    }
}
