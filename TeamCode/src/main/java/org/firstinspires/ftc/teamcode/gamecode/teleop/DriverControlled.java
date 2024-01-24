package org.firstinspires.ftc.teamcode.gamecode.teleop;

import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection.UP;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.visionPortal;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuGet;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuReset;
import static org.firstinspires.ftc.teamcode.operations.inputs.Target_inputs.*;
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
import org.firstinspires.ftc.teamcode.operations.outputs.driverStation.Caption;

@TeleOp
public class DriverControlled extends Target_operations {
    double speed = 0.5; // speed used when using d_pad
    // it should really be called power not speed

    @Override
    public void runOpMode() { // runs each necessary mode
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
        // grabs the motors from the hardware map and sets the direction to reverse or forward
        forwardMotors(false,true,false,true);
        // if the camera is not plugged it still run the robot, the try catch fixes that:
        // camera and use it for april tag
        initAprilTag(hardwareMap, DeviceNames.DEFAULT_CAMERA.hardwareMapName(), telemetry);
        // imuGet(hardwareMap, imu Device Name, logo direction, USB direction);
        imuGet(hardwareMap, DeviceNames.DEFAULT_IMU.hardwareMapName(), LEFT.name(), UP.name());
        // above is where defining the direction of the control hub for the imu


        // uncomment to map the pixel motor and use it with encoders
        // ConfigureMotorPixel.mapMotor(hardwareMap, "pixel");
        // PixelMotorMovements.motorEncoder();
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

        driveRaw(frontLeftPower,frontRightPower,backLeftPower,backRightPower); // sends the motors each a power according to the joystick and math commands

        // uncomment this to set the 'a' button on gamepad2 to move the pixel motor
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