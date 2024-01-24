package org.firstinspires.ftc.teamcode.gamecode.autonomous;

import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.forwardMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.mapMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.CentricMovements.fieldCentric.turn;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.drive;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.backwardAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.forwardAuto;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.strafeRightAuto;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.turnLeftAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.turnRightAuto;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.Encoder;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.operations.Target_operations;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Wheels;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Encoders;

@Autonomous(name="Team Props", group="auto")
public class TeamProps extends Target_operations {

    DcMotor arm;
    DistanceSensor sensorRange;

    @Override
    public void runOpMode() throws InterruptedException {
        // LinearOpMode that calls a different form of OpMode:
        runInit();
        while(opModeInInit()){runInitLoop();}
        waitForStart();
        runStart();
        if (isStopRequested()){runStop();} // stop OpMode if the button is pressed
        while (opModeIsActive()) {runLoop();}
    }

    @Override
    public void runInit() {
        //arm = hardwareMap.dcMotor.get("arm");
        mapMotors(hardwareMap, Wheels.FRONT_LEFT.abbreviation(),Wheels.FRONT_RIGHT.abbreviation(),Wheels.BACK_LEFT.abbreviation(),Wheels.BACK_RIGHT.abbreviation());
        forwardMotors(true,false,true,false);
        // ^ set motor directions
        initAprilTag(hardwareMap, "Webcam 1", telemetry);
        sensorRange = hardwareMap.get(DistanceSensor.class, "color");
        Encoders.clear();


    }

    @Override
    public void runInitLoop(){}

    @Override
    public void runStart() {
        backwardAuto(5,1,500); // away from wall.
        sleep(2000);
        turnRightAuto(800, 1, 500); // straighten out.
        sleep(2000);
        forwardAuto(5,1,500);
        sleep(2000);
        strafeRightAuto(5,1,500);
        /*sleep(2500);
        backwardAuto(5,1,500);
        sleep(2500);
        strafeRightAuto(3, 1,500);
        if (sensorRange.getDistance(DistanceUnit.INCH) <= 20) {
            backwardAuto(5,1,500);
        }*/
    }
    @Override
    public void runStop() {
        stopAll();
    }
    @Override
    public void runLoop() {
        telemetry.addData("distance ", sensorRange.getDistance(DistanceUnit.INCH));
        telemetry.addData("position FL ", fl.getCurrentPosition());
        telemetry.addData("position FR ", fr.getCurrentPosition());
        telemetry.addData("position BL ", bl.getCurrentPosition());
        telemetry.addData("position BR ", br.getCurrentPosition());
        telemetry.update();
    }
}

