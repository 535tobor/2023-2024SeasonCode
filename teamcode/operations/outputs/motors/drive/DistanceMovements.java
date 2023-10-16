package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.HardcoreMovements.*;

public class DistanceMovements {
    static double ticksPerMovements = 20; // using motors 20:1
    static double ticksPerInchForward = 5;
    // ticksPer141Inches = ???;
    static double ticksPerInchRight = 5;
    static double ticksPerDegree = 5;
    public static void forward(double inches, double speed) {
        int tp = (int) (inches*(ticksPerInchForward*ticksPerMovements));
        Encoders.use();
        Encoders.clear();
        Encoders.target(tp/2, tp/2, tp, tp);
        Encoders.go();
        drive(speed/2,speed/2,speed,speed);
        

    }

    public static void right(double inches, double speed) {
        int tp = (int) (inches*(ticksPerInchRight*ticksPerMovements));
        Encoders.use();
        Encoders.clear();
        Encoders.target(tp, tp, tp, tp);
        Encoders.go();
        drive(speed,speed,speed,speed);
        

    }

    public static void turnRight(double degrees, double speed) {
        int tp = (int) (degrees*(ticksPerDegree*ticksPerMovements));
        Encoders.use();
        Encoders.clear();
        Encoders.target(tp, tp, tp, tp);
        Encoders.go();
        drive(speed,speed,speed,speed);
        

    }
}
