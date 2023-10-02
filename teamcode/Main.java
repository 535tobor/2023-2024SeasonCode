package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.*;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.configureMotors.*;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.pixelMotor.ConfigureMotorPixel.pixelMotor;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.operations.TargetOperations;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.barMotor.BarMotorMovements;

@TeleOp
public class Main extends TargetOperations {

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
        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        // This button choice was made so that it is hard to hit on accident,
        // it can be freely changed based on preference.
        // The equivalent button is start on Xbox-style controllers.
        if (gamepad1.options) {
            imu.resetYaw();
        }

        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // Rotate the movement direction counter to the bot's rotation
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        double frontLeftPower = (rotY + rotX + rx) / denominator;
        double backLeftPower = (rotY - rotX + rx) / denominator;
        double frontRightPower = (rotY - rotX - rx) / denominator;
        double backRightPower = (rotY + rotX - rx) / denominator;

        if (gamepad2.a) {
            pixelMotor.setPower(1); // set speed to pixel motor to pick up pixels
        }
        else if (gamepad2.y) { // run to position for bar motor
            pixelMotor.setPower(0);
            BarMotorMovements.rotate(50, 1);

        }

        fl.setPower(frontLeftPower);
        bl.setPower(backLeftPower);
        fr.setPower(frontRightPower);
        br.setPower(backRightPower);


        // speed is still there, more force given to joystick, more power.

    }

    @Override
    public void runStop() {

    }
}