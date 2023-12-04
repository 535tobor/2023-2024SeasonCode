package org.firstinspires.ftc.teamcode.autonomous;

import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.forwardMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.mapMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.hardcoredMovements.EachMotorSet.drive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.operations.TargetOperations;
import org.firstinspires.ftc.teamcode.operations.Wheels;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Encoders;

@Autonomous(name="Red, Board Side", group="auto")
public class RedBoardSide extends TargetOperations {
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
        //arm = hardwareMap.dcMotor.get("arm");
        mapMotors(hardwareMap, Wheels.FRONT_LEFT.abbreviation(),Wheels.FRONT_RIGHT.abbreviation(),Wheels.BACK_LEFT.abbreviation(),Wheels.BACK_RIGHT.abbreviation());
        forwardMotors(true,false,true,false);
        // ^ set motor directions
        initAprilTag(hardwareMap, "Webcam 1");
        Encoders.clear();

    }

    @Override
    public void runInitLoop() {

    }

    @Override
    public void runStart() {
        // must set up methods for this later
        Encoders.clear();
        Encoders.target(-1000,-1000,-1000,-1000); // - must be to go forward
        Encoders.go();
        drive(500,500,500,500);
        sleep(500); // wait this makes sure that the robot does not strafe when it should not

        Encoders.clear();
        Encoders.target(-1800,1800,1800,-1800); // - must be to go forward
        Encoders.go();
        drive(-500,500,500,-500);
    }

    @Override
    public void runLoop() {
        telemetry.addData("position FL ", fl.getCurrentPosition());
        telemetry.addData("position FR ", fr.getCurrentPosition());
        telemetry.addData("position BL ", bl.getCurrentPosition());
        telemetry.addData("position BR ", br.getCurrentPosition());
        telemetry.update();
    }

    @Override
    public void runStop() {

    }

}