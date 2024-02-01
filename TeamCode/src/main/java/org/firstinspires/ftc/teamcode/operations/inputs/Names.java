package org.firstinspires.ftc.teamcode.operations.inputs;

public enum Names {
    UNKNOWN("unknown");
    // enums are used for easier calling of variables

    private final String text;

    Names(final String text) {this.text = text;}

    public String getString() {
        return text;
    }
}
