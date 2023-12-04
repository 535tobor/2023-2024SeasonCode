package org.firstinspires.ftc.teamcode.refrence.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
public class DriveByDistanceConcept {
    /*
    public void driveForward(double speed, double distance){
        double newPosLeft = encoderLeft.getCurrentPosition() + (distance/cm_per_tick);
        ;
        while ((opModeIsActive()) && (encoderLeft.getCurrentPosition() < newPosLeft)){
            setPowerAll(speed);
        }
        setPowerAllZero();
    }

    ^ using a while loop is a way I can tell if the robot's odometers has moved according to what I want
    This way I can accurately tell the robot to move a distance without using motor encoders, just odometers
    */
}
