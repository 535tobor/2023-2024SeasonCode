package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.*;
import static org.firstinspires.ftc.teamcode.operations.outputs.DriverStation.output;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.HardcoreMovements.*;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.configureMotors.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.operations.TargetOperations;

@Autonomous(name="Blue, Board Side")
public class BlueBoardSide extends TargetOperations {

    @Override
    public void runOpMode() throws InterruptedException {
        // LinearOpMode that calls a different form of OpMode:
        runInit();
        while(opModeInInit()){runInitLoop();}
        waitForStart();
        runStart();
        if (isStopRequested()){runStop();} // stop OpMode if the button is pressed
        while (opModeIsActive()) {runLoop();}
        visionPortal.close(); // close view for camera
    }

    @Override
    public void runInit() {
        mapMotors(hardwareMap, "fr","fl","br","bl");
        forwardMotors(false,true,false,true);
        // ^ set motor directions
        initAprilTag(hardwareMap, "Camera 1");
    }

    @Override
    public void runInitLoop(){}

    @Override
    public void runStart() {}
    @Override
    public void runStop() {
        stopAll();
    }
    @Override
    public void runLoop() {

        setAprilTagVariables();
        // ^ this line puts the april tag number into a variable, along with other april tag infromation
        if (gamepad1.a) {
            drive(0.5,0.5, 0.25,0.25);
        }
        else {
            driveStop();
        }

        if (gamepad1.x) {
            drive(0.5,-0.5, 0.25,-0.25);
        }
        else {
            driveStop();
        }

        if (gamepad1.b) {
            drive(-0.5,0.5, -0.25,0.25);
        }
        else {
            driveStop();
        }
        output(telemetry, tagId);
        if (tagId == 1){
            output(telemetry, "The First Tag!");
            runStop();
        }
        else if (tagId == 2){
            output(telemetry,"The Second Tag!");
            runStop();
        }
        else if (tagId == 3){
            output(telemetry,"The Third Tag!");
            runStop();
        }

    }
}

