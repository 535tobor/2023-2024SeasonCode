package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.hardcoredMovements.EachMotorSet.driveRaw;


public class DistanceMovements {
    static double ticksPerMovements = 20.0; // using motors 20:1

    static double ticksPerInchForward = 0.5;
    // ticksPer120Inches = ???;
    static double ticksPerInchRight = 5.0;
    static double ticksPerDegree = 5.0;
    public static void forward(double inches, double speed) {
        int tp = (int) (inches*(ticksPerInchForward*ticksPerMovements));
        Encoders.use();
        Encoders.clear();
        Encoders.target(tp, tp, tp, tp);
        Encoders.go();
        driveRaw(speed,speed,speed,speed);
        

    }

    public static void right(double inches, double speed) {
        int tp = (int) (inches*(ticksPerInchRight*ticksPerMovements));
        Encoders.use();
        Encoders.clear();
        Encoders.target(tp, tp, tp, tp);
        Encoders.go();
        driveRaw(speed,speed,speed,speed);
        

    }

    public static void turnRight(double degrees, double speed) {
        int tp = (int) (degrees*(ticksPerDegree*ticksPerMovements));
        Encoders.use();
        Encoders.clear();
        Encoders.target(tp, tp, tp, tp);
        Encoders.go();
        driveRaw(speed,speed,speed,speed);
        

    }
}
