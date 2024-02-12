package org.firstinspires.ftc.teamcode.operations.outputs.driverStation;


import static org.firstinspires.ftc.teamcode.operations.outputs.driverStation.Caption.defultCaption;
import static org.firstinspires.ftc.teamcode.operations.outputs.driverStation.Target_driverStation.captionIf;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriverStation {
    public static void output(@NonNull Telemetry display, Object output) {
        // @NonNull : declares that the object is not at null value
        // display to telemetry without a caption
        display.addData("", output);
    }

    public static void output(@NonNull Telemetry display, String caption, Object output) {
        // @NonNull : declares that the object is not at null value
        // display to telemetry with a caption or with a default caption if desired
        // if string caption can be converted into boolean then use the boolean
        captionIf = Boolean.parseBoolean(caption);
        if (captionIf) {
            display.addData(defultCaption.nameValue(), output);
        }
        else {
            display.addData(caption, output);
        }
        display.update();
    }

    public static void outputMake(@NonNull Telemetry display, Object output) {
        // @NonNull : declares that the object is not at null value
        // display to telemetry without a caption
        display.addData("", output);
        display.update();
    }
}
