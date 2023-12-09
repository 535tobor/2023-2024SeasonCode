package org.firstinspires.ftc.teamcode.operations.inOut;

import static org.firstinspires.ftc.teamcode.operations.Target_operations.barMotorConfig;
import static org.firstinspires.ftc.teamcode.operations.Target_operations.pixelMotorConfig;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.pixelMotor.Target_pixelMotor.pixelMotor;

import org.firstinspires.ftc.teamcode.operations.outputs.motors.barMotor.BarMotorMovements;

public class Gamepad2 {

    public static void runPixelMotor(boolean button) {
        if (pixelMotorConfig) {
            // only do the code if pixelMotor is configured
            if (button) {
                pixelMotor.setPower(1);
            }
            else {
                pixelMotor.setPower(0);
            }
        }
    }


    public static void runBarMotor(boolean button) {
        if (barMotorConfig) {
            if (button) {
                BarMotorMovements.rotate(50,1);
            }
        }
    }
}