package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.List;
public abstract class TargetOperations extends LinearOpMode {

    public static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera
    public double firstTagId, tagId;
    public AprilTagDetection firstTagFound;
    public double tagsFound;
    public String tagName;

    List tagCenter, tagPosition;

    /**
     * The variable to store our instance of the AprilTag processor.
     */
    public AprilTagProcessor aprilTag;

    /**
     * The variable to store our instance of the vision portal.
     */
    public VisionPortal visionPortal;

    DcMotor fr;
    DcMotor fl;
    DcMotor br;
    DcMotor bl;
    IMU imu;

    public abstract void runInit();
    public abstract void runInitLoop();
    public abstract void runStart();
    public abstract void runLoop();
    public abstract void runStop();

    public void mapMotors (String frontLeftMotor, String frontRightMotor, String backLeftMotor, String backRightMotor) {
        fr = hardwareMap.dcMotor.get(frontRightMotor);
        fl = hardwareMap.dcMotor.get(frontLeftMotor);
        br = hardwareMap.dcMotor.get(backRightMotor);
        bl = hardwareMap.dcMotor.get(backLeftMotor);
    }

    public void forwardMotors (boolean frontLeftMotor, boolean frontRightMotor, boolean backLeftMotor, boolean backRightMotor) {

        if (frontRightMotor) {
            fr.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        else {
            fr.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if (frontLeftMotor) {
            fl.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        else {
            fl.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if (backRightMotor) {
            br.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        else {
            br.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if (backLeftMotor) {
            bl.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        else {
            bl.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }

    public void imu () {
        // default imu hardware map name
        imu("imu");
    }

    public void imu (String imuName) {
        // Retrieve the IMU from the hardware map
        imu = hardwareMap.get(IMU.class, imuName);
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);
    }

    // hardcoded movements:
        public void forward(double speed) {
            fr.setPower(speed);
            fl.setPower(speed);
            br.setPower(speed);
            bl.setPower(speed);

        }

        public void backward(double speed) {
            fr.setPower(speed);
            fl.setPower(speed);
            br.setPower(speed);
            bl.setPower(speed);

        }

        public void left(double speed) {
            fr.setPower(speed);
            fl.setPower(-speed);
            br.setPower(-speed);
            bl.setPower(speed);

        }

        public void right(double speed) {
            fr.setPower(-speed);
            fl.setPower(speed);
            br.setPower(speed);
            bl.setPower(-speed);

        }

        public void forwardLeft(double speed) {
            fr.setPower(speed);
            bl.setPower(speed);

        }

        public void forwardRight(double speed) {
            fl.setPower(speed);
            br.setPower(speed);

        }

        public void backwardLeft(double speed) {;
            fl.setPower(-speed);
            br.setPower(-speed);

        }

        public void backwardRight(double speed) {
            fr.setPower(-speed);
            bl.setPower(-speed);

        }

    public void turnRight(double speed) {
        fr.setPower(-speed);
        fl.setPower(speed);
        br.setPower(-speed);
        bl.setPower(speed);

    }

    public void turnLeft(double speed) {
        fr.setPower(speed);
        fl.setPower(-speed);
        br.setPower(speed);
        bl.setPower(-speed);

    }

    public void drive(double frontLeftSpeed, double frontRightSpeed, double backLeftSpeed, double backRightSpeed) {
        fr.setPower(frontLeftSpeed);
        fl.setPower(frontRightSpeed);
        br.setPower(backLeftSpeed);
        bl.setPower(backRightSpeed);

    }

    public void driveZeroPower() {
        drive(0,0,0,0);
    }

    public void output(Object output) {
        telemetry.addData("", output);
        telemetry.update();
    }

    public void initAprilTag(String CameraName) {

        // Create the AprilTag processor the easy way.
        aprilTag = AprilTagProcessor.easyCreateWithDefaults();

        visionPortal = VisionPortal.easyCreateWithDefaults(
                hardwareMap.get(WebcamName.class, CameraName), aprilTag);
        }

        public void initAprilTag() {
        // default hardware map name for the camera
            initAprilTag("Camera 1");
        }

    public void setAprilTagVariables() {

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

    public void stopAll() {
        requestOpModeStop();
    }
}
