package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements;


import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class EachMotorSet {
    public static void drive(double frontLeftSpeed, double frontRightSpeed, double backLeftSpeed, double backRightSpeed) {
        fr.setVelocity(frontRightSpeed*1000);
        fl.setVelocity(frontLeftSpeed*1000);
        br.setVelocity(backRightSpeed*1000);
        bl.setVelocity(backLeftSpeed*1000);
        // set raw power (voltage) to each motor individually

    }

    public static void driveAuto(double frontLeftSpeed, double frontRightSpeed, double backLeftSpeed, double backRightSpeed) {
        fr.setVelocity(frontRightSpeed);
        fl.setVelocity(frontLeftSpeed);
        br.setVelocity(backRightSpeed);
        bl.setVelocity(backLeftSpeed);
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
