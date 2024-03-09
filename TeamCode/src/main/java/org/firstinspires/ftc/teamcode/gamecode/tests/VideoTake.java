package org.firstinspires.ftc.teamcode.gamecode.tests;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

@TeleOp(name = "Video Take Better", group = "video")
public class VideoTake extends LinearOpMode {
    private static final String VIDEO_FILENAME = "my_video.mp4"; // Specify your desired
    public void runOpMode() {
        // Initialize OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // Create a VideoCapture object for Webcam 1
        VideoCapture webcam = new VideoCapture(0); // Use 0 for Webcam 1
        // Set video resolution (adjust as needed)
        webcam.set(Videoio.CAP_PROP_FRAME_WIDTH, 640);
        webcam.set(Videoio.CAP_PROP_FRAME_HEIGHT, 480);
        // Create a VideoWriter to save the video
        VideoWriter videoWriter = new VideoWriter(VIDEO_FILENAME,
                VideoWriter.fourcc('X', '2', '6', '4'), // Codec (adjust as needed)
                30, // Frames per second
                new Size(640, 480), // Frame size
                true); // Is color video
        // Wait for the start button
        waitForStart();
        while (opModeIsActive()) {
            Mat frame = new Mat();
            webcam.read(frame); // Read a frame from the webcam
            // Process the frame (you can add your own image processing here)
            // Write the frame to the video file
            videoWriter.write(frame);
            telemetry.addData("Status", "Recording video...");
            telemetry.update();
        }
        // Release resources
        webcam.release();
        videoWriter.release();
        telemetry.addData("Status", "Video saved: " + VIDEO_FILENAME);
        telemetry.update();
    }
}