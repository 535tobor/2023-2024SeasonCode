package org.firstinspires.ftc.teamcode.gamecode.teleop;

import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection.UP;
import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.inputs.Camera.visionPortal;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuGet;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuReset;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.forwardMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.mapMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.dpadMovements;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.fieldCentricMath;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.*;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.driveRaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.driveStop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.operations.inputs.DeviceNames;
import org.firstinspires.ftc.teamcode.operations.Target_operations;

@TeleOp
public class DriverControlled extends Target_operations {
    double speed = 0.5; // speed used when using hardcore mode
    // in the code it is called speed, but it should really be called power

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
        mapMotors(hardwareMap, "fl","fr","bl","br");
        forwardMotors(false,true,false,true);
        // ConfigureMotorBar.mapMotor("bar");
        // this is commented because it might show an error sense this motor is not configured and can't be because the motor is not yet connected.
        // ConfigureMotorPixel.mapMotor("pixel");
        imuGet(hardwareMap, DeviceNames.DEFAULT_IMU.hardwareMapName(), UP.name(), BACKWARD.name());
        initAprilTag(hardwareMap, DeviceNames.DEFAULT_CAMERA.hardwareMapName());

        // ConfigureMotorPixel.mapMotor(hardwareMap, "pixel");
        // PixelMotorMovements.motorEncoder(); // pixel motor not connected
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
        // calling the joystickMovements method is not needed here because the program calls that method if there is no input found within the dpadMovements method

        imuReset(gamepad1.options); // resets imu case of accidents or incidences
        fieldCentricMath(); // does the required math for Mecanum drive as well as getting imu for field centric

        //runPixelMotor(gamepad2.a);
        //runBarMotor(gamepad2.y);
        // just guesses to how the code might look, this part of the robot has not been built yet.

        driveRaw(frontLeftPower,frontRightPower,backLeftPower,backRightPower);
        // sets each motor to the speed given by the waypoints method
        // odometer will fix issues with the robot not moving directly forward.
        // ^ this is not a problem for TeleOp, but is a problem in autonomous.

        /* if (gamepad2.a) {

            PixelMotorMovements.rotate(100, 1);
        }
        else {
            PixelMotorMovements.rotate(100, 0);
        }

         */

    }

    @Override
    public void runStop() {
        visionPortal.close();
        driveStop(); // stops all robot movements an slams on the breaks

    }
}
