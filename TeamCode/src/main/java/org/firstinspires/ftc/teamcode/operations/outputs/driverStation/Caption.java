package org.firstinspires.ftc.teamcode.operations.outputs.driverStation;

public enum Caption {
    defultCaption("--> ");

    private final String name;

    Caption(final String name) {this.name = name;}

    public String nameValue() {
        return name;
    }
}