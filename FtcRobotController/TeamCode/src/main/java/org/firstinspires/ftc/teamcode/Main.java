package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.operations.inOut.Gamepad2.*;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.*;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuGet;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuReset;
import static org.firstinspires.ftc.teamcode.operations.outputs.DriverStation.output;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.HardcoreMovements.*;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.*;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.configureMotors.*;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.operations.TargetOperations;

@TeleOp
public class Main extends TargetOperations {

    double speed = 0.5; // speed used when using hardcore mode

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
        mapMotors(hardwareMap, "fr","fl","br","bl");
        forwardMotors(false,true,false,true);
        //ConfigureMotorBar.mapMotor("bar");
        // this is commented because it might show an error sense this motor is not configured and can't be because the motor is not yet connected.
        //ConfigureMotorPixel.mapMotor("pixel");
        imuGet(hardwareMap, "imu", "LEFT", "UP");
        initAprilTag(hardwareMap, "Camera 1");
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

        runPixelMotor(gamepad2.a);
        runBarMotor(gamepad2.y);
        // just guesses to how the code might look, this part of the robot has not been built yet.

        drive(frontLeftPower,backLeftPower,frontRightPower,backRightPower);
        // sets each motor to the speed given by the waypoints method

        // aligning with the April Tags:

        setDirection(gamepad1); // if the left bumper is pushed the direction left is set, and the same for the right bumper, direction is set defult to left
        setAprilTagToFind(gamepad1); // using the x | y | b buttons x as left, y as middle, and b as right, april tag to find is set to default x (1)
        // this only works with the blue side at the moment

        searchForTagByControlled(speed);

        if (tagFound) {
            output(telemetry, "desired tag is found!");
        }

    }

    @Override
    public void runStop() {
        driveStop(); // stops all robot movements an slams on the breaks

    }
}