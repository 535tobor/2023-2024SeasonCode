package org.firstinspires.ftc.teamcode.operations;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
public abstract class TargetOperations extends LinearOpMode implements TargetSetup {

    public void stopAll() {
        requestOpModeStop();
    }

}