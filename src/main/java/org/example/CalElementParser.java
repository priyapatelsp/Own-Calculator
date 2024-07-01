package org.example;

import org.example.Element.CalElement;
import org.example.Element.CalElementType;

import java.util.ArrayList;
import java.util.List;

public class CalElementParser {
    private String input;
    private int position;

    public CalElementParser(String input) {
        this.input = input;
        this.position = 0;
    }

    public List<CalElement> parseElement() {
        List<CalElement> tokens = new ArrayList<>();

        while (position < input.length()) {
            char currentChar = input.charAt(position);

            if (Character.isDigit(currentChar)) {
                tokens.add(processNumber());
            } else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') {
                tokens.add(new CalElement(CalElementType.OPERATOR,currentChar));
                position++;
            } else if (currentChar == '(') {
                tokens.add(new CalElement(CalElementType.LEFT_PAREN));
                position++;
            } else if (currentChar == ')') {
                tokens.add(new CalElement(CalElementType.RIGHT_PAREN));
                position++;
            } else {
                // Ignore spaces
                if (currentChar != ' ') {
                    throw new IllegalArgumentException("Invalid character in expression: " + currentChar);
                }
                position++;
            }
        }

        return tokens;
    }

    private CalElement processNumber() {
        StringBuilder sb = new StringBuilder();
        while (position < input.length() && (Character.isDigit(input.charAt(position)) || input.charAt(position) == '.')) {
            sb.append(input.charAt(position));
            position++;
        }
        double number = Double.parseDouble(sb.toString());
        return new CalElement(CalElementType.NUMBER, number);
    }
}
