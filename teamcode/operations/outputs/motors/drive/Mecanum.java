package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive;

import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imu;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.configureMotors.rx;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.configureMotors.x;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.configureMotors.y;

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

    public static void fieldCentricMath() {
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // Rotate the movement direction counter to the robot's rotation
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        double frontLeftPower = (rotY + rotX + rx) / denominator;
        double backLeftPower = (rotY - rotX + rx) / denominator;
        double frontRightPower = (rotY - rotX - rx) / denominator;
        double backRightPower = (rotY + rotX - rx) / denominator;
    }
}
