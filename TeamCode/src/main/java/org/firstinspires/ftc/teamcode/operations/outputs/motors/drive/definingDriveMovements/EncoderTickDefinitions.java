package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements;

import static android.os.SystemClock.sleep;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.drive;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.driveAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.driveStop;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.Target_definingDriveMovements.strafeTicksPerInch;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.Target_definingDriveMovements.ticksPerInch;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.Target_definingDriveMovements.turningTicksPerDegree;

import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Encoders;


public class EncoderTickDefinitions {
    // define movements with encoders for Autonomous:
    public static void forwardAuto(double inches, long seconds, int encoderSpeed) { // forward good.
        int finalTicks = (int) (inches * ticksPerInch);
        Encoders.clear(); // resets wheel encoders
        Encoders.target(-finalTicks,-finalTicks,-finalTicks,-finalTicks); // sets target position
        Encoders.go(); // goes to target position
        driveAuto(-encoderSpeed,-encoderSpeed,-encoderSpeed,-encoderSpeed); // sets the velocity drive
        sleep(seconds*1000);
        driveStop();

    }



    public static void backwardAuto(double inches, long seconds, int encoderSpeed) { // forward good.
        int finalTicks = (int) (inches * ticksPerInch);
        Encoders.clear(); // resets wheel encoders
        Encoders.target(finalTicks,finalTicks,finalTicks,finalTicks); // sets target position
        Encoders.go(); // goes to target position
        driveAuto(encoderSpeed,encoderSpeed,encoderSpeed,encoderSpeed); // sets the velocity drive
        sleep(seconds*1000);
        driveStop();

    }

    public static void strafeLeftAuto(double inches, long seconds, int encoderSpeed) { // strafe good. // this is left
        int finalTicks = (int) (inches * strafeTicksPerInch);
        Encoders.clear(); // resets wheel encoders // fl, fr, bl, br
        Encoders.target(finalTicks,-finalTicks,-finalTicks,finalTicks); // sets target position
        Encoders.go(); // goes to target position // fl, fr, bl, br
        driveAuto(encoderSpeed,-encoderSpeed,-encoderSpeed,encoderSpeed); // sets the velocity drive
        sleep(seconds*1000);
        driveStop();


    }

    public static void strafeRightAuto(double inches, long seconds, int encoderSpeed) { // strafe good. // this is left
        strafeLeftAuto(-inches,seconds,-encoderSpeed);
    }

    public static void turnRightAuto(double degrees, long seconds, int encoderSpeed) { // turn Left
        int finalTicks = (int) (degrees * turningTicksPerDegree);
        Encoders.clear(); // resets wheel encoders // fl, fr, bl, br
        Encoders.target(-finalTicks,finalTicks,-finalTicks,finalTicks); // sets target position
        Encoders.go(); // goes to target position // fl, fr, bl, br
        driveAuto(-encoderSpeed,encoderSpeed,-encoderSpeed,encoderSpeed); // sets the velocity drive
        sleep(seconds*1000);
        driveStop();
    }

    public static void turnLeftAuto(double degrees, long seconds, int encoderSpeed) { // turn
        // 500 * 3 (90 degrees)
        double degreesTurn = 500 * 3/90*degrees; // 1 degree multiplied by entered degrees
        turnRightAuto(-degreesTurn,seconds,-encoderSpeed);
    }

}
