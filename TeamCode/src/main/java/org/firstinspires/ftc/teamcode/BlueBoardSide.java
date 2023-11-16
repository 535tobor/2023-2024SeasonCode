package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.inputs.Camera.visionPortal;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.forwardMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.mapMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.DistanceMovements.forward;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.operations.TargetOperations;
import org.firstinspires.ftc.teamcode.operations.Wheels;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Encoders;

@Autonomous(name="Blue, Board Side")
public class BlueBoardSide extends TargetOperations {

    DcMotor arm;

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
        //arm = hardwareMap.dcMotor.get("arm");
        mapMotors(hardwareMap, Wheels.FRONT_LEFT.abbreviation(),Wheels.FRONT_RIGHT.abbreviation(),Wheels.BACK_LEFT.abbreviation(),Wheels.BACK_RIGHT.abbreviation());
        forwardMotors(true,false,true,false);
        // ^ set motor directions
        initAprilTag(hardwareMap, "Webcam 1");
        Encoders.clear();

    }

    @Override
    public void runInitLoop(){}

    @Override
    public void runStart() {
        forward(300, 1);

    }
    @Override
    public void runStop() {
        stopAll();
    }
    @Override
    public void runLoop() {

        telemetry.addData("position", br.getCurrentPosition());
        telemetry.update();
    }
}

