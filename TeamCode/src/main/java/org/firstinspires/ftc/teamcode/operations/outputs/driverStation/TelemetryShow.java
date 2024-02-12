package org.firstinspires.ftc.teamcode.operations.outputs.driverStation;

import static org.firstinspires.ftc.teamcode.operations.inOut.Configs.sensorRange;
import static org.firstinspires.ftc.teamcode.operations.inputs.Odometer.oLeft;
import static org.firstinspires.ftc.teamcode.operations.inputs.Odometer.oRight;
import static org.firstinspires.ftc.teamcode.operations.inputs.Target_inputs.imu;
import static org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton.button;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.Target_shaft.shaft;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.botHeading;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.Target_claw.claw;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class TelemetryShow {
    public static void allLoopMessages(org.firstinspires.ftc.robotcore.external.Telemetry telemetry) {

        telemetry.addData("claw: ",claw.getPosition());
        telemetry.addData("Distance in Inches: ",sensorRange.getDistance(DistanceUnit.INCH));
        telemetry.addData("touch sensor", button.isPressed());
        telemetry.addData("bot heading: ", botHeading);
        telemetry.addData("position FL ", fl.getCurrentPosition());
        telemetry.addData("position FR ", fr.getCurrentPosition());
        telemetry.addData("position BL ", bl.getCurrentPosition());
        telemetry.addData("position BR ", br.getCurrentPosition());
        telemetry.addData("degrees ", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.addData("arm", arm.getCurrentPosition());
        telemetry.addData("shaft", shaft.getCurrentPosition());
        telemetry.addData("oLeft", oLeft.getCurrentPosition());
        telemetry.addData("oRight", oRight.getCurrentPosition());
        telemetry.update();

    }
}
