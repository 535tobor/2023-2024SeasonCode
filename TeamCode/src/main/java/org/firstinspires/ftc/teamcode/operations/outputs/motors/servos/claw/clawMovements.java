package org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.Target_claw.claw;

public class clawMovements {
    // claw open and close to pick up pixels
    public static double clawValue = 0;

    public static void openClaw() {claw.setPosition(0.4);
    }

    public static void openClaw(double value) {
        claw.setPosition(value);
    }

    public static void closeClaw() {
        claw.setPosition(0.05);
    }
}
