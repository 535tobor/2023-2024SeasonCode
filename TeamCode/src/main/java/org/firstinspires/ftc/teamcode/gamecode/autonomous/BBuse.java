package org.firstinspires.ftc.teamcode.gamecode.autonomous;

import static android.os.SystemClock.sleep;
import static org.firstinspires.ftc.teamcode.operations.inOut.Configs.mapOtherThings;
import static org.firstinspires.ftc.teamcode.operations.inOut.Configs.sensorRange;
import static org.firstinspires.ftc.teamcode.operations.inOut.Configs.xButton;
import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.gamepadMovements.General.hasBeenPressedHere;
import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.gamepadMovements.General.useArmEncoders;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.setAprilTagVariables;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.tagId;
import static org.firstinspires.ftc.teamcode.operations.inputs.Target_inputs.imu;
import static org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton.button;
import static org.firstinspires.ftc.teamcode.operations.inputs.VisionScanner.readProp;
import static org.firstinspires.ftc.teamcode.operations.outputs.driverStation.DriverStation.outputMake;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.armMovements.rotateArm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.Target_shaft.shaft;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.*;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.forwardMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.mapMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.CentricMovements.fieldCentric.forward;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.driveAutoForward;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.driveRaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.driveStop;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.useDriveEncoders;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.backwardAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.forwardAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.forwardAutoKeepGo;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.forwardAutoKeepGoTeleOp;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.strafeLeftAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.strafeRightAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.turnLeftAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.closeClaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.openClaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.wristIn;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.wristMove;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.wristOut;

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

@Autonomous(name="BBuse boardScan", group="in use")
public class BBuse extends Target_operations {
    boolean hasBeenPressed = false;
    boolean xHasBeenPressed = false;
    Orientation direction;
    private static int teamprop = 0;
    private static int desiredAprilTag = 0;
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

        wristMove(0.6); // closest to 0
        // set wrist to a position needed to touch the ground

        // extend shaft somehow.

        // the claw closes so it will not hit anything as the arm lowers
        // in runInitLoop should be openClaw(); under if button is pressed, do once

    }

    @Override
    public void runInitLoop() {
        setAprilTagVariables();
        telemetry.addData("found: ", sensorRange.getDistance(DistanceUnit.INCH));
        telemetry.addData("imu: ", imu.getRobotYawPitchRollAngles());
        telemetry.addData("april tag: ", tagId);
        telemetry.update();

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
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shaft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        closeClaw();

        useDriveEncoders(true);
        useArmEncoders(true);




        rotateArm(500,1);



        setAprilTagVariables();
        // move to board and put a pixel on the board
        //rotateArm(5000,1);
        // sleep(2000); // this sleep should not be necessary
        //rotateArm(10,0.5);

        forwardAuto(1,1,500); // move away from the wall

        // find prop in two places to get a number for april tag

        readProp(telemetry, 35, 2,50);
        if (teamprop == 2) {
            desiredAprilTag = 2;
        }

        else {
            strafeLeftAuto(8,1,800);
            readProp(telemetry, 35, 1,50);
            if (teamprop == 1) {
                desiredAprilTag = 1;
            }
            else {
                desiredAprilTag = 3;
            }
        }

        telemetry.addData("desired April Tag:", desiredAprilTag);
        telemetry.update();
        strafeRightAuto(10,1,1000);







        //rotateArm(2,0.5); // arm up so it does not scrape the ground
        strafeLeftAuto(23,5,1000); // strafe towards the board side
        forwardAuto(18,2,1000); // forward so that the robot's side is parallel to the board
        turnLeftAuto(90,1,1000); // turn left so that the robot's front faces the board
        telemetry.addData("number", "1");
        telemetry.update();
        closeClaw();


        forwardAuto(10,2,1000); // up to the board enough
        telemetry.addData("number", "2");
        telemetry.update();

        strafeRightAuto(10,1,1000);
        telemetry.addData("number", "3");
        telemetry.addData("distance", sensorRange.getDistance(DistanceUnit.INCH));
        telemetry.update();

        // arm is already at 100
        // everything is set
        closeClaw();
        turnLeftAuto(5,1,800);

        rotateArm(5951,1);
        shaft.setTargetPosition(-747);
        shaft.setPower(1);
        shaft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(5000);


        while (sensorRange.getDistance(DistanceUnit.INCH) > 2) {
            forwardAutoKeepGo(1,1000);
            telemetry.addData("forward", "Go!");
            telemetry.addData("distance", sensorRange.getDistance(DistanceUnit.INCH));
            telemetry.addData("distance", sensorRange.getDistance(DistanceUnit.INCH));
            telemetry.update();
        }
        setAprilTagVariables();
        telemetry.addData("third tag id ", tagId);
        telemetry.update();
        if (tagId == 3) {
            telemetry.addData("third tag id ", tagId);
            telemetry.update();
        }

        wristMove(0.48); // closest to 0
        openClaw();
        backwardAuto(3,5,500);






    }

    @Override
    public void runLoop() {
        telemetry.addData("position FL ", fl.getCurrentPosition());
        telemetry.addData("position FR ", fr.getCurrentPosition());
        telemetry.addData("position BL ", bl.getCurrentPosition());
        telemetry.addData("position BR ", br.getCurrentPosition());
        telemetry.addData("imu ", imu.getRobotYawPitchRollAngles());
        telemetry.update();
    }

    @Override
    public void runStop() {

    }

}