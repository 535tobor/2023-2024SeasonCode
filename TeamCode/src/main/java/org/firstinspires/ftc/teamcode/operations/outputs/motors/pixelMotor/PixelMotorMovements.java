package org.firstinspires.ftc.teamcode.operations.outputs.motors.pixelMotor;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.pixelMotor.ConfigureMotorPixel.pixelMotor;

import com.qualcomm.robotcore.hardware.DcMotor;

public class PixelMotorMovements {

    public static void motorEncoder () {
        pixelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pixelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pixelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public static void rotate (int rotations, int speed) {
        pixelMotor.setTargetPosition(rotations);
        pixelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pixelMotor.setPower(speed);
    }

}
