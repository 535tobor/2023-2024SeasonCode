package org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.autoArmOp;

import static org.firstinspires.ftc.teamcode.operations.inOut.Configs.sensorRange;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.armMovements.rotateArm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.CentricMovements.fieldCentric.forward;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.drive;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.driveStop;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.forwardAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.openClaw;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class PixelOnBoard {
    public static void putPixelOnBoard() {
        rotateArm(500,1); // rotate arm to the correct angle for the board (low)
        while (sensorRange.getDistance(DistanceUnit.INCH) > 3) {
            // continue until distance between board is not more than 3 (until it is less than 3)
            forward(1);
        }
        driveStop();
        openClaw();
    }
}
