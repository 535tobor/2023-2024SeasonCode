package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.configureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.configureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.configureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.configureMotors.fr;

import com.qualcomm.robotcore.hardware.DcMotor;

public class HardcoreMovements {

    public static void forward(double speed) {
        fr.setPower(speed);
        fl.setPower(speed);
        br.setPower(speed);
        bl.setPower(speed);

    }

    public static void backward(double speed) {
        fr.setPower(speed);
        fl.setPower(speed);
        br.setPower(speed);
        bl.setPower(speed);

    }

    public static void left(double speed) {
        fr.setPower(speed);
        fl.setPower(-speed);
        br.setPower(-speed);
        bl.setPower(speed);

    }

    public static void right(double speed) {
        fr.setPower(-speed);
        fl.setPower(speed);
        br.setPower(speed);
        bl.setPower(-speed);

    }

    public static void forwardLeft(double speed) {
        fr.setPower(speed);
        bl.setPower(speed);

    }

    public static void forwardRight(double speed) {
        fl.setPower(speed);
        br.setPower(speed);

    }

    public static void backwardLeft(double speed) {;
        fl.setPower(-speed);
        br.setPower(-speed);

    }

    public static void backwardRight(double speed) {
        fr.setPower(-speed);
        bl.setPower(-speed);

    }

    public static void turnRight(double speed) {
        fr.setPower(-speed);
        fl.setPower(speed);
        br.setPower(-speed);
        bl.setPower(speed);

    }

    public static void turnLeft(double speed) {
        fr.setPower(speed);
        fl.setPower(-speed);
        br.setPower(speed);
        bl.setPower(-speed);

    }

    public static void drive(double frontLeftSpeed, double frontRightSpeed, double backLeftSpeed, double backRightSpeed) {
        fr.setPower(frontLeftSpeed);
        fl.setPower(frontRightSpeed);
        br.setPower(backLeftSpeed);
        bl.setPower(backRightSpeed);

    }

    public static void driveStop() {

        drive(0,0,0,0);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    // ^ set all motors to 0, this stops all movements

}
