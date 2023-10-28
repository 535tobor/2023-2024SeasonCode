package org.firstinspires.ftc.teamcode.operations.inputs;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.HardcoreMovements.*;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.List;

public class AprilTag {

    public static VisionPortal visionPortal;

    public static double firstTagId, tagId;
    public static AprilTagDetection firstTagFound;
    public static double tagsFound;
    public static String tagName;

    public static List tagCenter, tagPosition;

    public static AprilTagProcessor aprilTag;

    public static double alignTagControlled = 1;

    public static boolean tagFound = false;


    public static void initAprilTag(HardwareMap mapHardware, String CameraName) {

        // Create the AprilTag processor
        aprilTag = AprilTagProcessor.easyCreateWithDefaults();

        visionPortal = VisionPortal.easyCreateWithDefaults(
                mapHardware.get(WebcamName.class, CameraName), aprilTag);
    }

    public static void setAprilTagVariables() {
        // add AprilTag found information to set variables

        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        firstTagFound = currentDetections.get(0);
        firstTagId = firstTagFound.id;
        tagsFound = currentDetections.size();

        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {

                tagName = detection.metadata.name;
                tagPosition = new ArrayList();
                tagPosition.add(detection.ftcPose.x);
                tagPosition.add(detection.ftcPose.y);
                tagPosition.add(detection.ftcPose.z);
                tagPosition.add(detection.ftcPose.pitch);
                tagPosition.add(detection.ftcPose.roll);
                tagPosition.add(detection.ftcPose.yaw);
                tagPosition.add(detection.ftcPose.range);
                tagPosition.add(detection.ftcPose.bearing);
                tagPosition.add(detection.ftcPose.elevation);

            }
            else {

                tagName = Names.UNKNOWN.getString();
            }

            tagId = detection.id;

            tagCenter = new ArrayList();
            tagCenter.add(detection.center.x);
            tagCenter.add(detection.center.y);
        }
    }

    public static void searchForTagByControlled(Gamepad gamepad1, double speed) {

        if (tagId != alignTagControlled) {
            goDirection(gamepad1.left_bumper, gamepad1.right_bumper, speed); // if the desired AprilTag is not found then go the desired direction until the april tag is found
        }

        else if (tagId == alignTagControlled) {
            tagFound = true;
            // express output
        }

    }

    public static void setAprilTagToFind (Gamepad gamepad1) {
        // by default set to 1;

        if (gamepad1.x) {
            alignTagControlled = 1;
            // go to april tag 1
        }

        else if (gamepad1.y) {
            alignTagControlled = 2;
        }

        else if (gamepad1.b) {
            alignTagControlled = 3;
        }
    }

}
