package org.firstinspires.ftc.teamcode.operations.outputs.driverStation;

public enum Caption {
    defultCaption("--> ");
    // the default caption variable will be used when a caption is desired but no specific caption is wanted

    private final String name;

    Caption(final String name) {this.name = name;}

    public String nameValue() {
        return name;
    }
}