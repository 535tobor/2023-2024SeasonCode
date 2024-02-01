package org.firstinspires.ftc.teamcode.reference.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime ;


@Autonomous(name = "Forward backward", group = "TEST")
// ^ declare what category this program will fall under, Autonomous or TeleOp
@Disabled
public class Forwardbackward_Autonomous extends LinearOpMode {


    DcMotor frontRightMotor;
    DcMotor frontLeftMotor;
    DcMotor backRightMotor;
    DcMotor backLeftMotor;

    private ElapsedTime runtime = new ElapsedTime();
    double speed = 1; // set robot speed


    @Override
    public void runOpMode() {

        frontRightMotor = hardwareMap.get(DcMotor.class,"frontRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class,"frontLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class,"backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class,"backLeftMotor");


        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status:", "Robot is Initialized");

        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();


        frontRightMotor.setPower(speed);
        backRightMotor.setPower(speed);
        frontLeftMotor.setPower(speed);
        backLeftMotor.setPower(speed);
        // ^ move all wheels forward
        runtime.reset();
        // all wheels move forward for three seconds
        while (opModeIsActive() && (runtime.seconds() < 3.0)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        frontRightMotor.setPower(-speed);
        backRightMotor.setPower(-speed);
        frontLeftMotor.setPower(-speed);
        backLeftMotor.setPower(-speed);
        // ^ move all wheels backward
        runtime.reset();
        // all wheels move backward for three seconds
        while (opModeIsActive() && (runtime.seconds() < 3.0)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }


    }
}