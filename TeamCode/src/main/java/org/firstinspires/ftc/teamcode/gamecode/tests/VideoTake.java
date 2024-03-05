package org.firstinspires.ftc.teamcode.gamecode.tests; // Change to your team's package name

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

@TeleOp(name = "Capture a Video", group = "video")
public class VideoTake extends LinearOpMode {
    private VideoCapture webcam;

    @Override
    public void runOpMode() {
        // Initialize OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Connect to the webcam (assuming it's named "Webcam 1")
        webcam = new VideoCapture(0); // Use 0 for the first camera

        // Check if the webcam is opened successfully
        if (!webcam.isOpened()) {
            telemetry.addData("Error", "Unable to open webcam");
            telemetry.update();
            sleep(2000);
            return;
        }

        // Main loop
        while (!isStopRequested()) {
            Mat frame = new Mat();
            webcam.read(frame); // Read a frame from the webcam

            // Process the frame (you can add your own vision processing here)

            // Display the frame (optional)
            // You can use telemetry or other methods to display the processed frame

            // Release the frame
            frame.release();
        }

        // Release the webcam
        webcam.release();
    }
}