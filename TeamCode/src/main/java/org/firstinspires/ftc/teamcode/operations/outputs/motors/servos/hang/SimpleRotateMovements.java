package org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.hang;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.hang.Target_hang.hang;

public class SimpleRotateMovements {

    public static void releaseHang() {
        hang.setPosition(0); // let go of hang thing
    }

    public static void grabHang() {
        hang.setPosition(1); // set servo to the starting position in all initializations
    }
}
