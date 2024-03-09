package org.firstinspires.ftc.teamcode.gamecode.teleop;

import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.FieldCentric.initFieldCentric;
import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.FieldCentric.runLoopFieldCentric;
import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.gamepadMovements.General.generalControlSetup;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.visionPortal;
import static org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton.button;
import static org.firstinspires.ftc.teamcode.operations.inputs.oFreeSpin.Odometer.oLeft;
import static org.firstinspires.ftc.teamcode.operations.inputs.oFreeSpin.Odometer.oRight;
import static org.firstinspires.ftc.teamcode.operations.inputs.Target_inputs.cameraConnected;
import static org.firstinspires.ftc.teamcode.operations.outputs.driverStation.DriverStation.output;
import static org.firstinspires.ftc.teamcode.operations.outputs.driverStation.DriverStation.outputMake;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.Target_shaft.shaft;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.driveStop;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.closeClaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.openClaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.wristIn;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.wristOut;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.operations.Target_operations;

@TeleOp
public class FieldCentric extends Target_operations {
    double speed = 0.5; // speed used when using hardcore mode
    public static Servo wrist;
    boolean hasBeenPressed = false;
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
        outputMake(telemetry,"running init");
        initFieldCentric(hardwareMap,telemetry);
        arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void runInitLoop() {
        wristIn();
        closeClaw();
        if (button.isPressed() && !hasBeenPressed) {
            arm.setPower(-1);
            // move arm up only one notch
            // open claw so the robot fits in the 18x18 zone
            openClaw();
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
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shaft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //outputMake(telemetry,"running start");
        //claw.setPosition(claw.getPosition()+2000);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shaft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        oLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        oRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    @Override
    public void runLoop() {
        //output(telemetry, "running loop");
        runLoopFieldCentric(telemetry,gamepad1,gamepad2,speed);

    }

    @Override
    public void runStop() {
        outputMake(telemetry,"running stop");
        if (cameraConnected) {
            visionPortal.close();
        }
        driveStop(); // stops all robot movements an slams on the breaks

    }
}