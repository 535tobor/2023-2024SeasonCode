package org.firstinspires.ftc.teamcode.gamecode.autonomous;

import static org.firstinspires.ftc.teamcode.operations.inOut.Configs.mapOtherThings;
import static org.firstinspires.ftc.teamcode.operations.inOut.Configs.sensorRange;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton.button;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;
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
import org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Encoders;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Wheels;

@Autonomous(name="Blue, Board Side Guess", group="guess")
public class BlueBoardSideGuess extends Target_operations {
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

    }

    @Override
    public void runInitLoop() {
        telemetry.addData("found: ", sensorRange.getDistance(DistanceUnit.INCH));
        telemetry.update();

        openClaw();
        if (button.isPressed() && !hasBeenPressed) {
            arm.setPower(-1);
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
        openClaw(0.5);
        // guess that team prop is in middle
        closeClaw();
        sleep(2000);
        forwardAuto(27,3,500);
        openClaw();
        sleep(1000);
        backwardAuto(25,3,500);

        // park
        forwardAuto(3,2,500);
        strafeLeftAuto(39,4,800);
        // pixel pushed
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