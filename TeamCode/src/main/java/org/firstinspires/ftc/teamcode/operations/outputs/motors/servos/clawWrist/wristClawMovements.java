package org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist;

import static org.firstinspires.ftc.teamcode.gamecode.teleop.RobotCentric.wrist;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.Target_claw.claw;

public class wristClawMovements {
    // the wrist movements are linear
    // when the wrist is mentioned the code means linear servo
    // the linear servo is the "wrist", it rotates the claw up and down
    static double clawWristValue = 0;
    public static boolean doneUp = false;
    public static boolean doneDown = false;

    public static void wristOut() {
        wrist.setPosition(0.7); // 1
    }

    public static void wristMove(double value) {
        wrist.setPosition(value); // anything
    }

    public static void wristIn() {
        wrist.setPosition(0.3);
    } // 0


    public static void wristUpTeleOp() {
            clawWristValue += 0.08;
            //claw.setPosition(0.4);
            wrist.setPosition(clawWristValue);
            doneUp = true;
    }

    public static void wristDownTeleOp() {
            clawWristValue -= 0.08;
            //claw.setPosition(0.2);
            wrist.setPosition(clawWristValue);
            doneDown = true;
    }
}
