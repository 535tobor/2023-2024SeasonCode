package org.firstinspires.ftc.teamcode.operations.inputs;

import static org.firstinspires.ftc.teamcode.operations.inOut.Configs.sensorRange;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class VisionScanner {
    public static int teamprop = 0;
    public static boolean found = false;
    public static void readProp(Telemetry telemetry, int inchesDistance, int desiredTeamProp, int timesToScan) {
        int scanTimes = 0;
        found = false;
        while (!found) {
            telemetry.addData("teamprop", teamprop);
            telemetry.addData("distance", sensorRange.getDistance(DistanceUnit.INCH));
            telemetry.update();
            scanTimes += 1;
            // if something is found under 26 inches than it must be the team prop
            if (sensorRange.getDistance(DistanceUnit.INCH) < inchesDistance) {
                teamprop = desiredTeamProp;
                found = true;
            }
            // if after 20 scans there is no team prop found then give up and move on to the next tape
            if (scanTimes >= timesToScan) {
                // if not found teamprop is equal to 0
                teamprop = 0;
                found = true;
            }
        }
        telemetry.addData("teamprop", teamprop);
        telemetry.addData("distance", sensorRange.getDistance(DistanceUnit.INCH));
        telemetry.update();
    }
}
