package org.firstinspires.ftc.teamcode.operations.outputs;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriverStation {
    public static void output(Telemetry display, Object output) {
        // display to telemetry without a caption
        display.addData("", output);
    }
}
