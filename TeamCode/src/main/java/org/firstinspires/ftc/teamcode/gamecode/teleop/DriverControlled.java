package org.firstinspires.ftc.teamcode.gamecode.teleop;

import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection.UP;
import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection.RIGHT;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.visionPortal;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuGet;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuReset;
import static org.firstinspires.ftc.teamcode.operations.inputs.Target_inputs.cameraConnected;
import static org.firstinspires.ftc.teamcode.operations.inputs.Target_inputs.imu;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.forwardMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.mapMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.botHeading;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.dpadMovements;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.fieldCentricMath;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.backLeftPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.backRightPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.frontLeftPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.frontRightPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.drive;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.driveStop;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.armLift.arm.armMovements.armSet;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.armLift.arm.rotateButtons.armUseWithGamepad;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.openCloseButtons.clawUseWithGamepad;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.operations.Target_operations;
import org.firstinspires.ftc.teamcode.operations.inputs.DeviceNames;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.armLift.arm.ConfigureArm;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.ConfigureClaw;

@TeleOp
public class DriverControlled extends Target_operations {
    double speed = 0.5; // speed used when using hardcore mode
    DistanceSensor sensorRange;
    boolean rangePluggedIn;

    @Override
    public void runOpMode() {
        runInit();
        if (isStopRequested()){runStop();}
        while(opModeInInit()){runInitLoop();}
        waitForStart();
        runStart();
        if (isStopRequested()){runStop();}
        while (opModeIsActive()) {runLoop();}
    }



    @Override
    public void runInit() {
        mapMotors(hardwareMap, "fl","fr","bl","br");
        ConfigureArm.mapMotor(hardwareMap);
        ConfigureClaw.mapServo(hardwareMap);
        forwardMotors(false,true,false,true);
        imuGet(hardwareMap, DeviceNames.DEFAULT_IMU.hardwareMapName(), RIGHT.name(), UP.name());
        initAprilTag(hardwareMap, DeviceNames.DEFAULT_CAMERA.hardwareMapName(), telemetry);
        try {
            sensorRange = hardwareMap.get(DistanceSensor.class, "eye");
            rangePluggedIn = true;
        }
        catch (Exception e){
            rangePluggedIn = false;
        }

        armSet();
    }

    @Override
    public void runInitLoop() {
    }

    @Override
    public void runStart() {
    }

    @Override
    public void runLoop() {

        dpadMovements(gamepad1, speed); // sets waypoints to the d_pads's positions


        imuReset(gamepad1.options); // resets imu case of accidents or incidences
        fieldCentricMath(); // does the required math for Mecanum drive as well as getting imu for field centric

        telemetry.addData("bot heading: ", botHeading);

        // only display the distance sensor if it is plugged in
        if (rangePluggedIn) {
            telemetry.addData("distance ", sensorRange.getDistance(DistanceUnit.INCH));
        }

        telemetry.addData("position FL ", fl.getCurrentPosition());
        telemetry.addData("position FR ", fr.getCurrentPosition());
        telemetry.addData("position BL ", bl.getCurrentPosition());
        telemetry.addData("position BR ", br.getCurrentPosition());
        telemetry.addData("degrees ", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.update();


        clawUseWithGamepad(gamepad2); // using the claw (open/close)
        armUseWithGamepad(gamepad2); // using the arm (rotation)
        drive(frontLeftPower,frontRightPower,backLeftPower,backRightPower);
        // sets each motor to the encoder counts given by the waypoints method

    }

    @Override
    public void runStop() {
        if (cameraConnected) {
            visionPortal.close();
        }
        driveStop(); // stops all robot movements an slams on the breaks

    }
}