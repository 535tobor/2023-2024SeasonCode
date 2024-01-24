package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements;

import static android.os.SystemClock.sleep;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.drive;
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
        drive(-encoderSpeed,-encoderSpeed,-encoderSpeed,-encoderSpeed); // sets the velocity drive
        sleep(seconds*1000);
    }

    public static void backwardAuto(double inches, long seconds, int encoderSpeed) { // forward good.
        forwardAuto(-inches, seconds, -encoderSpeed);
    }

    public static void strafeLeftAuto(double inches, long seconds, int encoderSpeed) { // strafe good. // this is left
        int finalTicks = (int) (inches * strafeTicksPerInch);
        Encoders.clear(); // resets wheel encoders // fl, fr, bl, br
        Encoders.target(finalTicks,-finalTicks,-finalTicks,finalTicks); // sets target position
        Encoders.go(); // goes to target position // fl, fr, bl, br
        drive(encoderSpeed,-encoderSpeed,-encoderSpeed,encoderSpeed); // sets the velocity drive
        sleep(seconds*1000);
    }

    public static void strafeRightAuto(double inches, long seconds, int encoderSpeed) { // strafe good. // this is left
        strafeLeftAuto(-inches,seconds,-encoderSpeed);
    }

    public static void turnRightAuto(float degrees, long seconds, int encoderSpeed) { // turn Left
        int finalTicks = (int) (degrees * turningTicksPerDegree);
        Encoders.clear(); // resets wheel encoders // fl, fr, bl, br
        Encoders.target(-finalTicks,finalTicks,-finalTicks,finalTicks); // sets target position
        Encoders.go(); // goes to target position // fl, fr, bl, br
        drive(-encoderSpeed,encoderSpeed,-encoderSpeed,encoderSpeed); // sets the velocity drive
        sleep(seconds*1000);
    }

    public static void turnLeftAuto(float degrees, long seconds, int encoderSpeed) { // turn
        turnRightAuto(-degrees,seconds,-encoderSpeed);
    }

}
