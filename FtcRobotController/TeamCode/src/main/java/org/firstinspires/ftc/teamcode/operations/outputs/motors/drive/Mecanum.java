package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive;

import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imu;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.HardcoreMovements.driveStop;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.configureMotors.rx;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.configureMotors.x;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.configureMotors.y;

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
    public static String direction = "left";

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
            left(speed);
        }

        else if (gamepad1.dpad_right) {
            waypoints(0, speed, 0);
        }

        else if (gamepad1.dpad_up) {
            waypoints(speed, 0, 0);
        }

        else if (gamepad1.dpad_down) {
            waypoints(-speed, 0, 0);
        }

        else {
            joyStickMovements(gamepad1); // this way if no input is read from the d_pad then it looks for input given by the joysticks
            // if there is no input given by either then the robot is at rest.
        }
    }

    public static void joyStickMovements(Gamepad gamepad1) {
        waypoints(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x); // Remember, Y stick value is reversed
    }

    public static void left(double speed) {
        waypoints(0, -speed, 0);
    }

    public static void right(double speed) {
        waypoints(0, speed, 0);
    }

    public static void goDirection(double speed) {
        if (direction == "left") {
            left(speed);
        }

        else if (direction == "right") {
            right(speed);
        }
    }

    public static void setDirection(Gamepad gamepad1) {
        if (gamepad1.left_bumper) {
            direction = "left";
        }

        else if (gamepad1.right_bumper) {
            direction = "right";
        }
    }
}
