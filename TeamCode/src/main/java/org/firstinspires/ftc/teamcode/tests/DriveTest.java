package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.operations.TargetOperations;
@Autonomous(name="Drive Test", group="test")
@Disabled // test files are disabled if not in use
public class DriveTest extends TargetOperations {

    DcMotor frontLeftMotor;
    DcMotor backLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backRightMotor;

    // wants to turn left
    // means that right wheels are higher power than other

    // I am using only two motors so that way I can make the 2 motors go the same speeds
    // later I can do the last two
    // less fr less than 0.254615
    // more fr more than 0.254615
    // if right less the less
    // if left more the less
    double speed = 1; // speed used when using hardcore mode
    double blSpeed = 0;
    double brSpeed = 0;
    double flSpeed = speed;
    double frSpeed = speed;

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
        frontLeftMotor = hardwareMap.dcMotor.get("fl");
        backLeftMotor = hardwareMap.dcMotor.get("bl");
        frontRightMotor = hardwareMap.dcMotor.get("fr");
        backRightMotor = hardwareMap.dcMotor.get("br");

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void runInitLoop() {

    }

    @Override
    public void runStart() {

    }

    @Override
    public void runLoop() {
        forward();
    }

    @Override
    public void runStop() {

    }

    public void forward() {
        frontLeftMotor.setPower(flSpeed);
        backLeftMotor.setPower(blSpeed);
        frontRightMotor.setPower(frSpeed);
        backRightMotor.setPower(brSpeed);
    }

    public void backward() {
        frontLeftMotor.setPower(-flSpeed);
        backLeftMotor.setPower(-blSpeed);
        frontRightMotor.setPower(-frSpeed);
        backRightMotor.setPower(-brSpeed);
    }

    public void leftward() {
        frontLeftMotor.setPower(-flSpeed);
        backLeftMotor.setPower(blSpeed);
        frontRightMotor.setPower(frSpeed);
        backRightMotor.setPower(-brSpeed);
    }

    public void rightward() {
        frontLeftMotor.setPower(flSpeed);
        backLeftMotor.setPower(-blSpeed);
        frontRightMotor.setPower(-frSpeed);
        backRightMotor.setPower(brSpeed);
    }
}