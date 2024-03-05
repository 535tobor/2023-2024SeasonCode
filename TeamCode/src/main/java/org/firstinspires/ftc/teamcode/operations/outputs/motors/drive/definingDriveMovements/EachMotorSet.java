package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements;


import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class EachMotorSet {
    public static void drive(double frontLeftSpeed, double frontRightSpeed, double backLeftSpeed, double backRightSpeed) {
        fr.setVelocity(frontRightSpeed*1500);
        fl.setVelocity(frontLeftSpeed*1500);
        br.setVelocity(backRightSpeed*1500);
        bl.setVelocity(backLeftSpeed*1500);
        // set speed

    }

    public static void driveRaw(double frontLeftSpeed, double frontRightSpeed, double backLeftSpeed, double backRightSpeed) {
        fr.setPower(frontRightSpeed);
        fl.setPower(frontLeftSpeed);
        br.setPower(backRightSpeed);
        bl.setPower(backLeftSpeed);
        // set raw power (voltage) to each motor individually

    }

    public static void driveAutoForward(double speed) {
        fr.setVelocity(-speed);
        fl.setVelocity(-speed);
        br.setVelocity(-speed);
        bl.setVelocity(-speed);
        // speed not power

    }

    public static void driveAutoBackward(double speed) {
        fr.setVelocity(speed);
        fl.setVelocity(speed);
        br.setVelocity(speed);
        bl.setVelocity(speed);
        // speed not power

    }

    public static void driveAuto(double frontLeftSpeed, double frontRightSpeed, double backLeftSpeed, double backRightSpeed) {
        fr.setVelocity(-frontRightSpeed);
        fl.setVelocity(-frontLeftSpeed);
        br.setVelocity(-backRightSpeed);
        bl.setVelocity(-backLeftSpeed);
        // set raw power (voltage) to each motor individually

    }

    public static void driveStop() {

        drive(0,0,0,0);
        fl.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        // stop all motors and pull on the brakes
    }
}
