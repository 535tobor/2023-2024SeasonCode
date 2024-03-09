package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements;


import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class EachMotorSet {
    public static void drive(double frontLeftSpeed, double frontRightSpeed, double backLeftSpeed, double backRightSpeed) {
        fr.setVelocity(frontRightSpeed*2000);
        fl.setVelocity(frontLeftSpeed*2000);
        br.setVelocity(backRightSpeed*2000);
        bl.setVelocity(backLeftSpeed*2000);
        // set speed

    }

    public static void useDriveEncoders(boolean encoders) {
        if (encoders) {
            fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        else {
            fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
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
