package org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.Target_claw.claw;

public class clawMovements {

    public static void openClaw() {
        claw.setPosition(1); // 0.5
    }

    public static void openClaw(double value) {
        claw.setPosition(value); // 0.5
    }

    public static void closeClaw() {
        claw.setPosition(0); // 0.15
    }
}
