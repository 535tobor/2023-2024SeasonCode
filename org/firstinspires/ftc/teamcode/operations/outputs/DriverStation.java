package org.firstinspires.ftc.teamcode.operations.outputs;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriverStation {
    public static void output(@NonNull Telemetry display, Object output) {
        // @NonNull : declares that the object is not at null value
        // display to telemetry without a caption
        display.addData("", output);
    }
}
