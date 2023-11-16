package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.operations.inputs.TeamProps.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

@Autonomous(name="Team props")
public class TeamProps extends LinearOpMode {

    /**
     * The variable to store our instance of the TensorFlow Object Detection processor.
     */
    private TfodProcessor tfod;

    /**
     * The variable to store our instance of the vision portal.
     */
    private VisionPortal visionPortal;

    private static final String TFOD_MODEL_ASSET = "toborprops.tflite";

    // Define the labels recognized in the model for TFOD (must be in training order!)
    private static final String[] LABELS = {
            "blue",
            "red",
    };

    @Override
    public void runOpMode() {

        initFlow(hardwareMap, "Webcam 1");

        // Wait for the DS start button to be touched.
        telemetry.addData("DS preview on/off", "3 dots, Camera Stream");
        telemetry.addData(">", "Touch Play to start OpMode");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {

                runFlow(gamepad1, telemetry);

                // Share the CPU.
                sleep(20);
            }
        }

        // Save more CPU resources when camera is no longer needed.
        visionPortal.close();

    }   // end runOpMode()
}