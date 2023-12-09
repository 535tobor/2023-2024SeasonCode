package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.fieldCentricMath;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.waypoints;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.*;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.driveRaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.CentricMovements.fieldCentric.left;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.CentricMovements.fieldCentric.right;

public class CentricMovements {
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
            driveRaw(frontLeftPower,backLeftPower,frontRightPower,backRightPower);
        }

        public static void right(double speed) {
            waypoints(0, speed, 0);
            fieldCentricMath();
            driveRaw(frontLeftPower,backLeftPower,frontRightPower,backRightPower);
        }
        public static void forward(double speed) {
            waypoints(speed, 0, 0);
            fieldCentricMath();
            driveRaw(frontLeftPower,backLeftPower,frontRightPower,backRightPower);
        }

        public static void backward(double speed) {
            waypoints(-speed, 0, 0);
            fieldCentricMath();
            driveRaw(frontLeftPower,backLeftPower,frontRightPower,backRightPower);
        }

        public static void turn(double speed) {
            fl.setPower(-speed);
            bl.setPower(-speed);

            fieldCentricMath();
        }


    }

}



