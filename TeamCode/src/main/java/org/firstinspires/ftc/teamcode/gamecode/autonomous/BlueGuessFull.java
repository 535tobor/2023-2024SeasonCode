package org.firstinspires.ftc.teamcode.gamecode.autonomous;

import static org.firstinspires.ftc.teamcode.operations.inOut.Configs.mapOtherThings;
import static org.firstinspires.ftc.teamcode.operations.inOut.Configs.sensorRange;
import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.gamepadMovements.General.useArmEncoders;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.setAprilTagVariables;
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
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.useDriveEncoders;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.backwardAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.forwardAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.forwardAutoKeepGo;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.strafeLeftAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.strafeRightAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.turnLeftAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.turnRightAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.Target_claw.claw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.closeClaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.openClaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.Target_claw.wrist;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.wristMove;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.operations.Target_operations;
import org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Encoders;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Wheels;

@Autonomous(name="Blue, FUll Guess", group="full guess")
public class BlueGuessFull extends Target_operations {
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
    public void runOpMode() {

        runInit();
        while(opModeInInit()){runInitLoop();}
        waitForStart();
        runStart();
        if (isStopRequested()){runStop();}
        while (opModeIsActive()) {runLoop();}
    }

    @Override
    public void runInit() {
        mapMotors(hardwareMap, Wheels.FRONT_LEFT.abbreviation(), Wheels.FRONT_RIGHT.abbreviation(), Wheels.BACK_LEFT.abbreviation(), Wheels.BACK_RIGHT.abbreviation());
        forwardMotors(true,false,true,false);
        // ^ set motor directions
        initAprilTag(hardwareMap, "Webcam 1", telemetry);
        Encoders.clear();

        mapOtherThings(hardwareMap);
        TouchSensorButton.mapDigital(hardwareMap); // button

        wristMove(0.5);
        rotateArm(1000,1);
        // set wrist to a position needed to touch the ground

        // extend shaft somehow.

        closeClaw();
        // the claw closes so it will not hit anything as the arm lowers
        // in runInitLoop should be openClaw(); under if button is pressed, do once

    }

    @Override
    public void runInitLoop() {
        telemetry.addData("found: ", sensorRange.getDistance(DistanceUnit.INCH));
        telemetry.addData("button arm: ", button.isPressed());
        telemetry.update();
        wrist.setPosition(0.56);

        if (button.isPressed() && !hasBeenPressed) {
            arm.setPower(-1);
            // move arm up only one notch
            // open claw so the robot fits in the 18x18 zone
            openClaw();
            hasBeenPressed = true;
        }
        else if (hasBeenPressed) {
            arm.setPower(0);
        }
        else {
            arm.setPower(1);
        }
    }

    @Override
    public void runStart() {
        // guess that team prop is in middle
        closeClaw();
        forwardAuto(30,3,800);
        openClaw();
        sleep(2000);
        backwardAuto(30,3,500);

        strafeLeftAuto(5,2,800);
        turnRightAuto(90,5,800);
        forwardAuto(8,2,800);
        closeClaw();
        turnLeftAuto(90,5,800);

        // park and pixel on board

        useDriveEncoders(true);
        useArmEncoders(true);

        setAprilTagVariables();
        closeClaw();
        // move to board and put a pixel on the board
        rotateArm(5000,1);
        // sleep(2000); // this sleep should not be necessary
        //rotateArm(10,0.5);

        forwardAuto(1,1,500); // move away from the wall
        //rotateArm(2,0.5); // arm up so it does not scrape the ground
        strafeLeftAuto(23,3,1000); // strafe towards the board side
        forwardAuto(18,2,1000); // forward so that the robot's side is parallel to the board
        turnLeftAuto(90,1,1000); // turn left so that the robot's front faces the board
        telemetry.addData("number", "1");
        telemetry.update();
        sleep(2000);


        forwardAuto(10,2,1000); // up to the board enough
        rotateArm(50,1);
        telemetry.addData("number", "2");
        telemetry.update();
        sleep(2000);

        strafeRightAuto(5,1,1000);
        telemetry.addData("number", "3");
        sleep(2000);
        telemetry.addData("distance", sensorRange.getDistance(DistanceUnit.INCH));
        telemetry.update();

        // arm is already at 100
        // everything is set

        rotateArm(3640,1);
        sleep(5000);
        shaft.setTargetPosition(-911);
        shaft.setPower(1);
        shaft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wristMove(0.3); // closest to 0


        while (sensorRange.getDistance(DistanceUnit.INCH) > 3) {
            forwardAutoKeepGo(1,1000);
            telemetry.addData("forward", "Go!");
            telemetry.addData("distance", sensorRange.getDistance(DistanceUnit.INCH));
            telemetry.addData("distance", sensorRange.getDistance(DistanceUnit.INCH));
            telemetry.update();
        }
        openClaw();


        useDriveEncoders(false);
        useArmEncoders(false);
    }

    @Override
    public void runLoop() {
        telemetry.addData("position FL ", fl.getCurrentPosition());
        telemetry.addData("position FR ", fr.getCurrentPosition());
        telemetry.addData("position BL ", bl.getCurrentPosition());
        telemetry.addData("position BR ", br.getCurrentPosition());
        telemetry.update();
    }

    @Override
    public void runStop() {

    }

}