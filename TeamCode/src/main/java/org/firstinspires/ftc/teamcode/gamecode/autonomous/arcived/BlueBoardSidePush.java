package org.firstinspires.ftc.teamcode.gamecode.autonomous.arcived;

import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection.UP;
import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection.RIGHT;
import static org.firstinspires.ftc.teamcode.operations.inOut.Configs.mapOtherThings;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuGet;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.forwardMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.mapMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.drive;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.forwardAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.strafeLeftAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.turnLeftAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.operations.Target_operations;
import org.firstinspires.ftc.teamcode.operations.inputs.DeviceNames;
import org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton;
import org.firstinspires.ftc.teamcode.operations.outputs.driverStation.TelemetryShow;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Wheels;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Encoders;

@Autonomous(name="Blue, Board Side Push", group="push")
public class BlueBoardSidePush extends Target_operations {

    DcMotor arm;

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
        mapMotors(hardwareMap, Wheels.FRONT_LEFT.abbreviation(), Wheels.FRONT_RIGHT.abbreviation(), Wheels.BACK_LEFT.abbreviation(), Wheels.BACK_RIGHT.abbreviation());
        forwardMotors(true, false, true, false);
        imuGet(hardwareMap, DeviceNames.DEFAULT_IMU.hardwareMapName(), RIGHT.name(), UP.name());
        // ^ set motor directions
        initAprilTag(hardwareMap, "Webcam 1", telemetry);
        Encoders.clear();

        mapOtherThings(hardwareMap);
        TouchSensorButton.mapDigital(hardwareMap); // button


    }

    @Override
    public void runInitLoop(){}

    @Override
    public void runStart() {
        forwardAuto(3,1,500);
        strafeLeftAuto(37,1,500);
    }
    @Override
    public void runStop() {
        stopAll();
    }
    @Override
    public void runLoop() {

        TelemetryShow.allLoopMessages(telemetry);
    }
}
