package org.example.Element;

public class CalElement {
    private CalElementType type;
    private char valueOp;
    private double value;// Only applicable for NUMBER tokens

    public CalElement(CalElementType type) {
        this.type = type;
    }

    public CalElement(CalElementType type, double value) {
        this.type = type;
        this.value = value;
    }
    public CalElement(CalElementType type, char valueOp) {
        this.type = type;
        this.valueOp = valueOp;
    }

    public CalElementType getType() {
        return type;
    }
    public char getValueChar() {
        return valueOp;
    }

    public double getValue() {
        return value;
    }
}
