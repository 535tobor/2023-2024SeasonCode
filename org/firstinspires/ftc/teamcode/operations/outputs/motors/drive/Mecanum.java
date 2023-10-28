package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive;

import static org.firstinspires.ftc.teamcode.operations.inputs.TargetInputs.*;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.HardcoreMovements.*;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Mecanum {

    static double botHeading;
    static double rotX;
    static double rotY;
    static double denominator;
    public static double frontLeftPower;
    public static double frontRightPower;
    public static double backLeftPower;
    public static double backRightPower;
    public static double x,y,rx;

    public static void fieldCentricMath() {
        botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // Rotate the movement direction counter to the robot's rotation
        rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        frontLeftPower = (rotY + rotX + rx) / denominator;
        backLeftPower = (rotY - rotX + rx) / denominator;
        frontRightPower = (rotY - rotX - rx) / denominator;
        backRightPower = (rotY + rotX - rx) / denominator;
    }

    public static void waypoints(double Y, double X, double RX) {
        // adds waypoint values to variables for motor movements and speeds
        y = Y;
        x = X;
        rx = RX;
    }

    public static void dpadMovements(Gamepad gamepad1, double speed) {
        // Mecanum using the dpad, if drivers want it, its there.
        // how this works is it sets the x and y values depending on what button is pressed.
        // it is replacing the joystick value with its own.
        if (gamepad1.dpad_left) {
            fieldCentric.left(speed);
        }

        else if (gamepad1.dpad_right) {
            fieldCentric.right(speed);
        }

        else if (gamepad1.dpad_up) {
            fieldCentric.forward(speed);
        }

        else if (gamepad1.dpad_down) {
            fieldCentric.backward(speed);
        }

        else {
            joyStickMovements(gamepad1); // this way if no input is read from the d_pad then it looks for input given by the joysticks
            // if there is no input given by either then the robot is at rest.
        }
    }

    public static void joyStickMovements(Gamepad gamepad1) {
        waypoints(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x); // Remember, Y stick value is reversed
    }
}