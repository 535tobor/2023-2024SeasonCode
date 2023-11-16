package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.HardcoreMovements.fieldCentric.left;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.HardcoreMovements.fieldCentric.right;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.backLeftPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.backRightPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.fieldCentricMath;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.frontLeftPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.frontRightPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.waypoints;

import com.qualcomm.robotcore.hardware.DcMotor;

public class HardcoreMovements {

    public static void drive(double frontLeftSpeed, double frontRightSpeed, double backLeftSpeed, double backRightSpeed) {
        fr.setPower(frontRightSpeed);
        fl.setPower(frontLeftSpeed);
        br.setPower(backRightSpeed);
        bl.setPower(backLeftSpeed);

    }


    public static void driveStop() {

        drive(0,0,0,0);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    // ^ set all motors to 0, this stops all movements

    public static void goDirection(boolean left, boolean right, double speed) {
        if (left) {
            left(speed);
        }

        else if (right) {
            right(speed);
        }
    }

    public static class fieldCentric {
        // these methods must be ran in the loop() of this project
        public static void left(double speed) {
            waypoints(0, -speed, 0);
            fieldCentricMath();
            drive(frontLeftPower,backLeftPower,frontRightPower,backRightPower);
        }

        public static void right(double speed) {
            waypoints(0, speed, 0);
            fieldCentricMath();
            drive(frontLeftPower,backLeftPower,frontRightPower,backRightPower);
        }
        public static void forward(double speed) {
            waypoints(speed, 0, 0);
            fieldCentricMath();
            drive(frontLeftPower,backLeftPower,frontRightPower,backRightPower);
        }

        public static void backward(double speed) {
            waypoints(-speed, 0, 0);
            fieldCentricMath();
            drive(frontLeftPower,backLeftPower,frontRightPower,backRightPower);
        }

        public static void turn(double speed) {
            fl.setPower(-speed);
            bl.setPower(-speed);

            fieldCentricMath();
        }


    }

}



