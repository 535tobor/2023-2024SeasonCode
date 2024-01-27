package org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.armLift.arm;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.armLift.arm.Target_arm.arm;

import com.qualcomm.robotcore.hardware.DcMotor;

public class armMovements {

    public static void armSet() {
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public static void armStop() {
        arm.setPower(0);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public static void rotateArm(int rotateAmount, double power) {
        arm.setTargetPosition(rotateAmount);
        arm.setPower(20*power);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
