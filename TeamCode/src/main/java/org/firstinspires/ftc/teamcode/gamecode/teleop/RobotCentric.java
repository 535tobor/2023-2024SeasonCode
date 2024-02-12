package org.firstinspires.ftc.teamcode.gamecode.teleop;

import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.RobotCentric.initRobotCentric;
import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.RobotCentric.runLoopRobotCentric;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.visionPortal;
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
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.openClaw;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.operations.Target_operations;

@TeleOp
public class RobotCentric extends Target_operations {

    double speed = 0.5; // speed used when using hardcore mode
    public static Servo wrist;
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
        initRobotCentric(hardwareMap,telemetry);
    }

    @Override
    public void runInitLoop() {
        outputMake(telemetry,"running init loop");
    }

    @Override
    public void runStart() {
        outputMake(telemetry,"running start");
        //claw.setPosition(claw.getPosition()+2000);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shaft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        openClaw(0.5);

    }

    @Override
    public void runLoop() {
        output(telemetry, "running loop");
        runLoopRobotCentric(telemetry,gamepad1,gamepad2,speed);

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