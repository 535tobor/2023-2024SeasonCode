package org.firstinspires.ftc.teamcode.gamecode.autonomous;

import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection.UP;
import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection.RIGHT;
import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.RobotCentric.sensorRange;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuGet;
import static org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton.button;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.armMovements.rotateArm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.Target_shaft.shaft;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.forwardMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.mapMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.backwardAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.forwardAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.strafeLeftAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.strafeRightAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.turnLeftAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.turnRightAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.Target_definingDriveMovements.ticksPerInch;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.Target_claw.claw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.closeClaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.openClaw;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.operations.Target_operations;
import org.firstinspires.ftc.teamcode.operations.inputs.DeviceNames;
import org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Encoders;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Wheels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Autonomous(name="Blue, Board Side (TEST)", group="auto")
public class BlueBoardSideTest extends Target_operations {
    boolean hasBeenPressed = false;
    Orientation direction;
    private static int teamprop = 0;
    private static boolean found = false;
    private static double distanceUse = 0;
    private static double storedDistance = 0;
    // and save the heading
    double botHeading;
    double botTargetHeading;

    @Override
    public void runOpMode() throws InterruptedException {
        // LinearOpMode that calls a different form of OpMode:
        runInit();
        while (opModeInInit()) {
            runInitLoop();
        }
        waitForStart();
        runStart();
        if (isStopRequested()) {
            runStop();
        } // stop OpMode if the button is pressed
        while (opModeIsActive()) {
            runLoop();
        }
    }

    @Override
    public void runInit() {
        //arm = hardwareMap.dcMotor.get("arm");
        mapMotors(hardwareMap, Wheels.FRONT_LEFT.abbreviation(), Wheels.FRONT_RIGHT.abbreviation(), Wheels.BACK_LEFT.abbreviation(), Wheels.BACK_RIGHT.abbreviation());
        forwardMotors(true, false, true, false);
        imuGet(hardwareMap, DeviceNames.DEFAULT_IMU.hardwareMapName(), RIGHT.name(), UP.name());
        // ^ set motor directions
        initAprilTag(hardwareMap, "Webcam 1", telemetry);
        Encoders.clear();

        sensorRange = hardwareMap.get(DistanceSensor.class, "left_eye");

        arm = hardwareMap.get(DcMotorEx.class, "arm");
        shaft = hardwareMap.get(DcMotor.class, "shaft");
        claw = hardwareMap.get(Servo.class, "claw");
        TouchSensorButton.mapDigital(hardwareMap); // button


    }

    @Override
    public void runInitLoop() {
        telemetry.addData("found: ", sensorRange.getDistance(DistanceUnit.INCH));
        telemetry.update();

        openClaw();
        if (button.isPressed() && !hasBeenPressed) {
            arm.setPower(-0.1);
            hasBeenPressed = true;
        }
        else if (hasBeenPressed) {
            arm.setPower(0);
            arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        else {
            arm.setPower(0.5);
        }
    }

    @Override
    public void runStart() {
        telemetry.addData("found: ", sensorRange.getDistance(DistanceUnit.INCH));
        telemetry.update();
        int scanTimes = 0;
        found = false;
        while (!found) {
            telemetry.addData("teamprop",teamprop);
            telemetry.addData("distance",sensorRange.getDistance(DistanceUnit.INCH));
            telemetry.update();
            scanTimes += 1;
            // if something is found under 26 inches than it must be the team prop
            if (sensorRange.getDistance(DistanceUnit.INCH) < 35) {
                teamprop = 2;
                found = true;
            }
            // if after 20 scans there is no team prop found then give up and move on to the next tape
            if (scanTimes >= 50) {
                teamprop = 0;
                found = true;
            }
        }
        telemetry.addData("teamprop",teamprop);
        telemetry.addData("distance",sensorRange.getDistance(DistanceUnit.INCH));
        telemetry.update();

        closeClaw(); // close
        sleep(500);
        // lift arm above ground, holding 1 pixel
        rotateArm(400, 1);
        sleep(1000);


        if (teamprop == 2) { // if the middle team prop is found then go to it and drop the pixel
            telemetry.addData("", "found!");
            telemetry.addData("teamprop",teamprop);
            telemetry.addData("distance",sensorRange.getDistance(DistanceUnit.INCH));
            telemetry.update();
            forwardAuto(27, 5, 800);
            openClaw();


        }


        else {
            scanTimes = 0;
            found = false;
            // if the middle team prop is not found then search for the team prop on zone 1
            strafeLeftAuto(10,1,800);
            /*turnLeftAuto(500 * 3, 2, 500); // 90 degrees
            openClaw();*/

            while (!found) {
                telemetry.addData("teamprop",teamprop);
                telemetry.addData("distance",sensorRange.getDistance(DistanceUnit.INCH));
                telemetry.update();
                scanTimes += 1;
                // if something is found under 26 inches than it must be the team prop
                if (sensorRange.getDistance(DistanceUnit.INCH) < 35) {
                    teamprop = 1;
                    found = true;
                }
                // if after 20 scans there is no team prop found then give up and move on to the next tape
                if (scanTimes >= 50) {
                    teamprop = 0;
                    found = true;
                }
            }

            if (teamprop == 1) {
                telemetry.addData("", "found!");
                telemetry.addData("teamprop",teamprop);
                telemetry.addData("distance",sensorRange.getDistance(DistanceUnit.INCH));
                telemetry.update();
                forwardAuto(23, 5, 800);
                strafeLeftAuto(5,1,800);
                openClaw();
            }

            else {
                strafeRightAuto(10,1,800);
                forwardAuto(27, 5, 800);
                strafeLeftAuto(5,1,800);
                turnRightAuto(500 * 3,1,800);
                openClaw();
            }
        }
        strafeLeftAuto(40,2,1000);
        }

        /*if (sensorRange.getDistance(DistanceUnit.INCH) < 9) {
            teamprop = 2;
            openClaw();
            backwardAuto(20, 2, 800);
            strafeLeftAuto(40, 5, 800);
            keepGoing();
        } else {
            strafeRightAuto(3, 1, 500);
            if (sensorRange.getDistance(DistanceUnit.INCH) < 9) {
                teamprop = 2;
                openClaw();
                backwardAuto(20, 2, 800);
                strafeLeftAuto(40, 5, 800);
                keepGoing();
            }*/
                /*turnLeftAuto(500 * 3, 2, 500);
                if (sensorRange.getDistance(DistanceUnit.INCH) < 9) {
                    teamprop = 1;
                    openClaw();
                    sleep(1000);
                    closeClaw();
                    backwardAuto(38, 5, 500);
                } else {
                    turnLeftAuto((500 * 3) * 2, 2, 500); // 180 degrees (about)
                    // no need to check due to process of elimination.
                    teamprop = 1;
                    openClaw();
                    sleep(1000);
                    closeClaw();
                    strafeLeftAuto(10, 5, 500);
                    forwardAuto(40, 5, 500);
                }
            }*/


            /*turnRightAuto(500*3,2,500); // 90 degrees
            backwardAuto(8,1,500);
            strafeRightAuto(5,1,500);
            turnRightAuto(500*3/2,2,500); // 45 degrees

            if (sensorRange.getDistance((DistanceUnit.INCH)) < 10) {
                forwardAuto(2,1,500);
                openClaw();
            }*/
    public static void keepGoing() {

        if (teamprop == 2) {
            forwardAuto(25,2,800);
            turnLeftAuto(500*3,2,500);
            backwardAuto(8,2,500);

            if (sensorRange.getDistance(DistanceUnit.INCH) < 9) {
                backwardAuto(2,2,500);
            }
            else if (sensorRange.getDistance(DistanceUnit.INCH) > 12) {
                forwardAuto(2,2,500);
            }
        }
    }

    @Override
    public void runStop() {
        stopAll();
    }
    @Override
    public void runLoop() {

        telemetry.addData("position FL ", fl.getCurrentPosition());
        telemetry.addData("position FR ", fr.getCurrentPosition());
        telemetry.addData("position BL ", bl.getCurrentPosition());
        telemetry.addData("position BR ", br.getCurrentPosition());
        telemetry.addData("TargetTicksPerInch (final) : ", 30 * ticksPerInch);
        telemetry.addData("Sensor Range (Inches) : ", sensorRange.getDistance(DistanceUnit.INCH));
        telemetry.addData("Bot Heading : ", sensorRange.getDistance(DistanceUnit.INCH));
        telemetry.addData("Bot Heading Final : ", sensorRange.getDistance(DistanceUnit.INCH));
        telemetry.update();
    }
}

