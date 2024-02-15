package org.firstinspires.ftc.teamcode.gamecode.autonomous;

import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection.UP;
import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection.RIGHT;
import static org.firstinspires.ftc.teamcode.operations.inOut.Configs.mapOtherThings;
import static org.firstinspires.ftc.teamcode.operations.inOut.Configs.sensorRange;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuGet;
import static org.firstinspires.ftc.teamcode.operations.inputs.VisionScanner.readProp;
import static org.firstinspires.ftc.teamcode.operations.inputs.oFreeSpin.Odometer.oLeft;
import static org.firstinspires.ftc.teamcode.operations.inputs.oFreeSpin.Odometer.oRight;
import static org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton.button;
import static org.firstinspires.ftc.teamcode.operations.inputs.oFreeSpin.Target_oFreeSpin.oReset;
import static org.firstinspires.ftc.teamcode.operations.inputs.oFreeSpin.Target_oFreeSpin.oTicksGoInches_Backward;
import static org.firstinspires.ftc.teamcode.operations.inputs.oFreeSpin.Target_oFreeSpin.oTicksGoInches_Forward;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.forwardMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.mapMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.driveAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.driveStop;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.strafeLeftAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.turnLeftAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.turnRightAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.openClaw;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.operations.Target_operations;
import org.firstinspires.ftc.teamcode.operations.inputs.DeviceNames;
import org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton;
import org.firstinspires.ftc.teamcode.operations.outputs.driverStation.TelemetryShow;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Encoders;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Wheels;

@Autonomous(name="Blue, Board Side Mega", group="mega")
public class BlueBoardSideMega extends Target_operations {
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
        // once this is tested, it should work anywhere on the field (as far as the scanning the teamprop and putting the purple pixel on the tape part)
        oReset(); // reset encoders for free spinning wheels
        readProp(telemetry, 35, 2,50);
        oTicksGoInches_Forward(28);
        readProp(telemetry, 10, 2,50);
        if (teamprop == 2) {
            openClaw();
            // the pixel should score if the prop is in the middle here

            // this is so the robot is in the same end position after this 20 points. Parked, the robot can do things from there.
            oTicksGoInches_Backward(28);
            // should be at wall start position at this time
        }

        else {
            // teamprop is 0 (not found)
            // look at zone 1
            turnLeftAuto(90,1,1000);
            readProp(telemetry, 10, 2,50);
            if (teamprop == 1) {
                openClaw();
                // the pixel should score if the prop is in zone 1 here
                turnRightAuto(90,1,1000);
                oTicksGoInches_Backward(28);
            }

            else {
                turnRightAuto(90*2,1,1000);
                // teamprop is 0 (not found)
                // it is not necessary to scan for the last zone, this is because process of elimination (zone 3)
                openClaw();
                turnLeftAuto(90,1,1000);
                oTicksGoInches_Backward(28);
            }
        }

        // to board
        oTicksGoInches_Backward(3);
        strafeLeftAuto(38,1,1000);
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

