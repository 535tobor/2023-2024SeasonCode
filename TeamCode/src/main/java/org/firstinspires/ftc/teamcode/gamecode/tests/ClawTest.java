package org.firstinspires.ftc.teamcode.gamecode.tests;

import static org.firstinspires.ftc.teamcode.gamecode.teleop.RobotCentric.wrist;
import static org.firstinspires.ftc.teamcode.operations.inOut.Configs.mapOtherThings;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton.button;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.Target_shaft.shaft;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.forwardMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.mapMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.Target_claw.claw;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.operations.Target_operations;
import org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Encoders;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Wheels;

@Autonomous(name="Pixel Grab", group="test")
public class ClawTest extends Target_operations {
    boolean hasBeenPressed = false;
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
        mapMotors(hardwareMap, Wheels.FRONT_LEFT.abbreviation(), Wheels.FRONT_RIGHT.abbreviation(), Wheels.BACK_LEFT.abbreviation(), Wheels.BACK_RIGHT.abbreviation());
        forwardMotors(true,false,true,false);
        // ^ set motor directions
        initAprilTag(hardwareMap, "Webcam 1", telemetry);
        Encoders.clear();

        mapOtherThings(hardwareMap);
        TouchSensorButton.mapDigital(hardwareMap); // button

        claw.setPosition(0.78);



    }

    @Override
    public void runInitLoop() {

        wrist.setPosition(0.5);
        sleep(20);
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



    }

    @Override
    public void runLoop() {

    }

    @Override
    public void runStop() {

    }

}