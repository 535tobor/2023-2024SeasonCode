package org.firstinspires.ftc.teamcode.operations.outputs;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

public class DriverStation {
    public static void output(Object output) {
        // display to telemetry without a caption
        telemetry.addData("", output);
        telemetry.update();
    }
}
