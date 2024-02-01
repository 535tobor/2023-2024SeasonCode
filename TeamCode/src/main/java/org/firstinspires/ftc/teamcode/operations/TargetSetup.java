package org.firstinspires.ftc.teamcode.operations;// public is not needed, because Interface has public by default

interface TargetSetup {
     /*
     this interface was made so that
     both LinearOpMode and OpMode can be
     ran within the same class
      */
     void runInit();
     void runInitLoop();
     void runStart();
     void runLoop();
     void runStop();
}
