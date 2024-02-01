package org.firstinspires.ftc.teamcode.gamecode.teleop;

import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.FieldCentric.initFieldCentric;
import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.FieldCentric.runLoopFieldCentric;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.visionPortal;
import static org.firstinspires.ftc.teamcode.operations.inputs.Target_inputs.cameraConnected;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.driveStop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.teamcode.operations.Target_operations;

@TeleOp
public class FieldCentric extends Target_operations {
    double speed = 0.5; // speed used when using hardcore mode
    DistanceSensor sensorRange;
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
        initFieldCentric(hardwareMap,telemetry);
    }

    @Override
    public void runInitLoop() {
    }

    @Override
    public void runStart() {
        //claw.setPosition(claw.getPosition()+2000);

    }

    @Override
    public void runLoop() {

        runLoopFieldCentric(telemetry,gamepad1,gamepad2,speed);

    }

    @Override
    public void runStop() {
        if (cameraConnected) {
            visionPortal.close();
        }
        driveStop(); // stops all robot movements an slams on the breaks

    }
}