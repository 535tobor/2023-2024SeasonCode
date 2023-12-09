package org.firstinspires.ftc.teamcode.operations.outputs.motors.pixelMotor;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.pixelMotor.Target_pixelMotor.pixelMotor;

import com.qualcomm.robotcore.hardware.DcMotor;

public class PixelMotorMovements {

    public static void motorEncoder () {
        pixelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pixelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public static void rotate (int rotations, int power) {
        pixelMotor.setTargetPosition(rotations);
        pixelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pixelMotor.setPower(power);
    }

}
