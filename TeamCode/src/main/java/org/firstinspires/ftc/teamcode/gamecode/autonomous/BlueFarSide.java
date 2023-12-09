package org.firstinspires.ftc.teamcode.gamecode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.operations.Target_operations;

@Autonomous(name="Blue, Far Side", group="auto")
public class BlueFarSide extends Target_operations {

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

    }

    @Override
    public void runInitLoop() {

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