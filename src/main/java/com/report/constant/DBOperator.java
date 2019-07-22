package com.report.constant;


public enum DBOperator {

    AND(0),
    OR(1);

    private int value;

    private DBOperator(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
