package org.firstinspires.ftc.teamcode.gamecode.teleop;

import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.RobotCentric.initRobotCentric;
import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.RobotCentric.runLoopRobotCentric;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.visionPortal;
import static org.firstinspires.ftc.teamcode.operations.inputs.Target_inputs.cameraConnected;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.Target_shaft.shaft;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.driveStop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.operations.Target_operations;

@TeleOp
public class RobotCentric extends Target_operations {
    double speed = 0.5; // speed used when using hardcore mode
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
        initRobotCentric(hardwareMap,telemetry);
    }

    @Override
    public void runInitLoop() {
    }

    @Override
    public void runStart() {
        //claw.setPosition(claw.getPosition()+2000);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shaft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    @Override
    public void runLoop() {
        runLoopRobotCentric(telemetry,gamepad1,gamepad2,speed);

    }

    @Override
    public void runStop() {
        if (cameraConnected) {
            visionPortal.close();
        }
        driveStop(); // stops all robot movements an slams on the breaks

    }
}