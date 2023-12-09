package org.firstinspires.ftc.teamcode.operations;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class Target_operations extends LinearOpMode implements TargetSetup {

    public static boolean pixelMotorConfig = false;
    public static boolean barMotorConfig = false;

    public void stopAll() {
        requestOpModeStop();
    }

}
