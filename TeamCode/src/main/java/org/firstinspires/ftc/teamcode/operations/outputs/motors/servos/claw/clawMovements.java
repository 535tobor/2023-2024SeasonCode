package org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.Target_claw.claw;

public class clawMovements {
    public static int openTimes = 0;
    public static int closeTimes = 0;
    public static double clawOpen = 0;
    public static double clawClose = 0;

    public static void openClaw() {
        claw.setPosition(0.7); // 0.5
    }

    public static void closeClaw() {
        claw.setPosition(0.12); // 0.15
    }
}
