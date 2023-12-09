package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements;

import static android.os.SystemClock.sleep;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.drive;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.Target_definingDriveMovements.encoderSpeed;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.Target_definingDriveMovements.strafeTicksPerInch;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.Target_definingDriveMovements.ticksPerInch;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.Target_definingDriveMovements.turningTicksPerInch;

import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Encoders;

public class EncoderTickDefinitions {
    public void forward(double inches, long seconds) {
        int finalTicks = (int) (inches * ticksPerInch);
        Encoders.clear();
        Encoders.target(-finalTicks,-finalTicks,-finalTicks,-finalTicks);
        Encoders.go();
        drive(-encoderSpeed,-encoderSpeed,-encoderSpeed,-encoderSpeed);
        sleep(seconds*1000);
    }

    public void backward(double inches, long seconds) {
        int finalTicks = (int) (inches * ticksPerInch);
        Encoders.clear();
        Encoders.target(finalTicks,finalTicks,finalTicks,finalTicks);
        Encoders.go();
        drive(encoderSpeed,encoderSpeed,encoderSpeed,encoderSpeed);
        sleep(seconds*1000);
    }

    public void leftward(double inches, long seconds) {
        int finalTicks = (int) (inches * strafeTicksPerInch);
        Encoders.clear();
        Encoders.target(finalTicks,-finalTicks,-finalTicks,finalTicks);
        Encoders.go();
        drive(encoderSpeed,-encoderSpeed,-encoderSpeed,encoderSpeed);
        sleep(seconds*1000);
    }

    public void rightward(double inches, long seconds) {
        int finalTicks = (int) (inches * strafeTicksPerInch);
        Encoders.clear();
        Encoders.target(-finalTicks,finalTicks,finalTicks,-finalTicks);
        Encoders.go();
        drive(-encoderSpeed,encoderSpeed,encoderSpeed,-encoderSpeed);
        sleep(seconds*1000);
    }

    public void turnLeft(double inches, long seconds) {
        int finalTicks = (int) (inches * turningTicksPerInch);
        Encoders.clear();
        Encoders.target(-finalTicks,finalTicks,-finalTicks,finalTicks);
        Encoders.go();
        drive(-encoderSpeed,encoderSpeed,-encoderSpeed,encoderSpeed);
        sleep(seconds*1000);
    }

    public void turnRight(double inches, long seconds) {
        int finalTicks = (int) (inches * turningTicksPerInch);
        Encoders.clear();
        Encoders.target(finalTicks,-finalTicks,finalTicks,-finalTicks);
        Encoders.go();
        drive(encoderSpeed,-encoderSpeed,encoderSpeed,-encoderSpeed);
        sleep(seconds*1000);
    }
}
