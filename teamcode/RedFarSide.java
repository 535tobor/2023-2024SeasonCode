package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@Autonomous(name="Red, Far Side")
public class RedFarSide extends LCore {

    @Override
    public void runOpMode() {

        waitForStart();
        if (isStopRequested()) return;

        // move robot across april tags, stop robot when it finds the correct one


        while (opModeIsActive()) {

            //telemetryAprilTag();
        }
        visionPortal.close();
    }

}