package org.firstinspires.ftc.teamcode.operations.inputs;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.List;

public class AprilTag {

    public static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera

    public static VisionPortal visionPortal;

    public static double firstTagId, tagId;
    public static AprilTagDetection firstTagFound;
    public static double tagsFound;
    public static String tagName;

    public static List tagCenter, tagPosition;

    public static AprilTagProcessor aprilTag;


    public static void initAprilTag(String CameraName) {

        // Create the AprilTag processor
        aprilTag = AprilTagProcessor.easyCreateWithDefaults();

        visionPortal = VisionPortal.easyCreateWithDefaults(
                hardwareMap.get(WebcamName.class, CameraName), aprilTag);
    }

    public static void initAprilTag() {
        // default hardware map name for the camera
        initAprilTag("Camera 1"); // sets the name to the default name
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

                tagName = "unknown";
            }

            tagId = detection.id;

            tagCenter = new ArrayList();
            tagCenter.add(detection.center.x);
            tagCenter.add(detection.center.y);
        }
    }

}
