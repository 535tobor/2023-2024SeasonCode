package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class EachMotorSet {
    public static void driveRaw(double frontLeftSpeed, double frontRightSpeed, double backLeftSpeed, double backRightSpeed) {
        fr.setPower(frontRightSpeed);
        fl.setPower(frontLeftSpeed);
        br.setPower(backRightSpeed);
        bl.setPower(backLeftSpeed);

    }

    public static void drive(int frontLeftSpeed, int frontRightSpeed, int backLeftSpeed, int backRightSpeed) {
        fr.setVelocity(frontRightSpeed);
        fl.setVelocity(frontLeftSpeed);
        br.setVelocity(backRightSpeed);
        bl.setVelocity(backLeftSpeed);

    }

    public static void driveStop() {

        driveRaw(0,0,0,0);
        fl.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }
}
