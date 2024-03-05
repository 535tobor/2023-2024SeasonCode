package org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist;

import static org.firstinspires.ftc.teamcode.gamecode.teleop.RobotCentric.wrist;

public class wristClawMovements {
    // the wrist movements are linear
    // when the wrist is mentioned the code means linear servo
    // the linear servo is the "wrist", it rotates the claw up and down

    public static void wristOut() {
        wrist.setPosition(0.82); // 1
    }

    public static void wristMove(double value) {
        wrist.setPosition(value); // anything
    }

    public static void wristIn() {
        wrist.setPosition(0.3);
    } // 0
}
