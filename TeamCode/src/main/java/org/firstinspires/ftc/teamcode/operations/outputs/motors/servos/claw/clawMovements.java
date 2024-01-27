package org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.Target_claw.claw;

public class clawMovements {

    private static int clawPosition = 0;

    public static void openClawSet () {
        claw.setPosition(15); // open position
    }

    public static void openClawAdd () {
        clawPosition += 1;
        claw.setPosition(clawPosition); // open position added
    }

    public static void closeClawAdd () {
        clawPosition -= 1;
        claw.setPosition(clawPosition); // close position added
    }

    public static void closeClawSet () {
        claw.setPosition(5); // close position
    }

}
