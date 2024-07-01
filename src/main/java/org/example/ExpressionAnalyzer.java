package org.example;

import org.example.Element.CalElement;

import java.util.List;
import java.util.Stack;

public class ExpressionAnalyzer {
    public double analyze(String expression) {
        List<CalElement> calElements = new CalElementParser(expression).parseElement();
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (CalElement calElement : calElements) {
            switch (calElement.getType()) {
                case NUMBER:
                    values.push(calElement.getValue());
                    break;
                case OPERATOR:
                    while (!operators.isEmpty() && hasPrecedence(calElement.getValue(), operators.peek())) {
                        values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                    }
                    operators.push(calElement.getValueChar());
                    break;
                case LEFT_PAREN:
                    operators.push('(');
                    break;
                case RIGHT_PAREN:
                    while (operators.peek() != '(') {
                        values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                    }
                    operators.pop(); // Remove '(' from stack
                    break;
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private boolean hasPrecedence(double op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
        return true;
    }

    private double applyOperation(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}
