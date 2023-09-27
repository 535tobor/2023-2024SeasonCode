package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue, Board Side")
public class BlueBoardSide extends TargetOperations {

    @Override
    public void runOpMode() throws InterruptedException {
        runInit();
        while(opModeInInit()){runInitLoop();}
        waitForStart();
        runStart();
        if (isStopRequested()){runStop();}
        while (opModeIsActive()) {runLoop();}
        visionPortal.close();
    }

    @Override
    public void runInit() {
        mapMotors("fr","fl","br","bl");
        forwardMotors(false,true,false,true);
        initAprilTag();
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

        // when testing the botman's robot for a test bot make back wheels half speed (fr & fl = 1 then : br & bl = 0.5)

        setAprilTagVariables();
        if (gamepad1.a) {
            drive(0.5,0.5, 0.25,0.25);
        }
        else {
            driveZeroPower();
        }

        if (gamepad1.x) {
            drive(0.5,-0.5, 0.25,-0.25);
        }
        else {
            driveZeroPower();
        }

        if (gamepad1.b) {
            drive(-0.5,0.5, -0.25,0.25);
        }
        else {
            driveZeroPower();
        }
        output(tagId);
        if (tagId == 1){
            output("The First Tag!");
            runStop();
        }
        else if (tagId == 2){
            output("The Second Tag!");
            runStop();
        }
        else if (tagId == 3){
            output("The Third Tag!");
            runStop();
        }

    }
}

