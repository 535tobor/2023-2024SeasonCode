package org.firstinspires.ftc.teamcode.operations.inputs;

public enum Names {
    UNKNOWN("unknown");

    private final String text;

    Names(final String text) {this.text = text;}

    public String getString() {
        return text;
    }
}
