package org.firstinspires.ftc.teamcode.operations.inOut;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.pixelMotor.ConfigureMotorPixel.pixelMotor;

import org.firstinspires.ftc.teamcode.operations.outputs.motors.barMotor.BarMotorMovements;

public class Gamepad2 {

    public static void runPixelMotor(boolean button) {
        if (button) {
            pixelMotor.setPower(1);
        }
        else {
            pixelMotor.setPower(0);
        }
    }


    public static void runBarMotor(boolean button) {
        if (button) {
            BarMotorMovements.rotate(50,1);
        }
    }
}