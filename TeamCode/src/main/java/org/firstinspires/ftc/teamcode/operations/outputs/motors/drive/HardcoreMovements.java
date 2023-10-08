package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.configureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.configureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.configureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.configureMotors.fr;

import com.qualcomm.robotcore.hardware.DcMotor;

public class HardcoreMovements {

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
