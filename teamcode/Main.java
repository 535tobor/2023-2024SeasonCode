package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.operations.inOut.Gamepad2.*;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.tagId;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuGet;
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
        mapMotors("fr","fl","br","bl");
        forwardMotors(false,true,false,true);
        //ConfigureMotorBar.mapMotor("bar");
        //ConfigureMotorPixel.mapMotor("pixel");
        imuGet("imu", "LEFT", "UP");
    }

    @Override
    public void runInitLoop() {

    }

    @Override
    public void runStart() {
    }

    @Override
    public void runLoop() {
        waypoints(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x); // Remember, Y stick value is reversed
        imuReset(gamepad1.options); // resets imu case of accidents or incidences
        fieldCentricMath(); // does the required math for Mecanum drive as well as getting imu for field centric
        runPixelMotor(gamepad2.a);
        runBarMotor(gamepad2.y);
        drive(frontLeftPower,backLeftPower,frontRightPower,backRightPower);

        /// Hardcore movements for Mecanum, but no turn:

        if (gamepad1.dpad_left) {
            left(speed);
        }

        else if (gamepad1.dpad_right) {
            right(speed);
        }

        else if (gamepad1.dpad_up) {
            forward(speed);
        }

        else if (gamepad1.dpad_down) {
            backward(speed);
        }

        else {
            driveStop();
        }

        ///

        if (gamepad1.x) {
            // go to april tag 1
            if (tagId != 1) {
                left(speed);
            }
            else if (tagId == 1) {
                output("tag 1");
            }
        }

    }

    @Override
    public void runStop() {

    }
}