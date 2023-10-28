package org.firstinspires.ftc.teamcode.operations;

public enum Wheels {
    FRONT_LEFT("fl"),
    FRONT_RIGHT("fr"),
    BACK_LEFT("bl"),
    BACK_RIGHT("br");

    private final String name;

    Wheels(final String name) {this.name = name;}

    public String abbreviation() {
        return name;
    }
}
