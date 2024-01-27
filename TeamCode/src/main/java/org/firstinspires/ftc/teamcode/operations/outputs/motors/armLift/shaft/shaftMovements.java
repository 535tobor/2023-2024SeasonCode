package org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.Target_shaft.shaft;

import com.qualcomm.robotcore.hardware.DcMotor;

public class shaftMovements {

    public static void shaftSet() {
        shaft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shaft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public static void shaftStop() {
        shaft.setPower(0);
        shaft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public static void moveShaft(int moveAmount, double power) {
        shaft.setTargetPosition(moveAmount);
        shaft.setPower(20*power);
        shaft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
