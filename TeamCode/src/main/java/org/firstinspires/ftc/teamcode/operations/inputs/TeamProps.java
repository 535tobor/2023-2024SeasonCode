package org.firstinspires.ftc.teamcode.operations.inputs;

import static org.firstinspires.ftc.teamcode.operations.inputs.Camera.visionPortal;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

public class TeamProps {

    /**
     * The variable to store our instance of the TensorFlow Object Detection processor.
     */
    public static TfodProcessor tfod;
    public static String prop;

    /**
     * The variable to store our instance of the vision portal.
     */

    private static final String TFOD_MODEL_ASSET = "toborprops.tflite";

    // Define the labels recognized in the model for TFOD (must be in training order!)
    private static final String[] LABELS = {
            "blue",
            "red",
    };

    public static void runFlow(Gamepad gamepad1, Telemetry telemetry) {
        telemetryFlow(telemetry);

        // Push telemetry to the Driver Station.
        telemetry.update();

        // Save CPU resources; can resume streaming when needed.
        if (gamepad1.dpad_down) {
            visionPortal.stopStreaming();
        } else if (gamepad1.dpad_up) {
            visionPortal.resumeStreaming();
        }


    }

    /**
     * Initialize the TensorFlow Object Detection processor.
     */
    public static void initFlow(HardwareMap hardwareMap, String CameraDeviceName) {

        // Create the TensorFlow processor by using a builder.
        tfod = new TfodProcessor.Builder()
                .setModelAssetName(TFOD_MODEL_ASSET)
                .setModelLabels(LABELS)

            // Use setModelAssetName() if the TF Model is built in as an asset.
            // Use setModelFileName() if you have downloaded a custom team model to the Robot Controller.
            //.setModelAssetName(TFOD_MODEL_ASSET)
            //.setModelFileName(TFOD_MODEL_FILE)

            //.setModelLabels(LABELS)
            //.setIsModelTensorFlow2(true)
            //.setIsModelQuantized(true)
            //.setModelInputSize(300)
            //.setModelAspectRatio(16.0 / 9.0)

            .build();

        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder();

        // Set the camera (webcam vs. built-in RC phone camera).
        builder.setCamera(hardwareMap.get(WebcamName.class, CameraDeviceName));

        // Choose a camera resolution. Not all cameras support all resolutions.
        //builder.setCameraResolution(new Size(640, 480));

        // Enable the RC preview (LiveView).  Set "false" to omit camera monitoring.
        //builder.enableCameraMonitoring(true);

        // Set the stream format; MJPEG uses less bandwidth than default YUY2.
        //builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);

        // Choose whether or not LiveView stops if no processors are enabled.
        // If set "true", monitor shows solid orange screen if no processors enabled.
        // If set "false", monitor shows camera view without annotations.
        //builder.setAutoStopLiveView(false);

        // Set and enable the processor.
        builder.addProcessor(tfod);

        // Build the Vision Portal, using the above settings.
        visionPortal = builder.build();

        // Set confidence threshold for TFOD recognitions, at any time.
        //tfod.setMinResultConfidence(0.75f);

        // Disable or re-enable the TFOD processor at any time.
        //visionPortal.setProcessorEnabled(tfod, true);

    }   // end method initTfod()

    /**
     * Add telemetry about TensorFlow Object Detection (TFOD) recognitions.
     */
    public static void telemetryFlow(Telemetry telemetry) {

        List<Recognition> currentRecognitions = tfod.getRecognitions();
        telemetry.addData("# Objects Detected", currentRecognitions.size());

        // Step through the list of recognitions and display info for each one.
        for (Recognition recognition : currentRecognitions) {
            double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
            double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;

            telemetry.addData(""," ");
            telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
            telemetry.addData("- Position", "%.0f / %.0f", x, y);
            telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
            telemetry.addData("", currentRecognitions.lastIndexOf(recognition));

            if (currentRecognitions.lastIndexOf(recognition) == 1) {
                Recognition recognition1 = currentRecognitions.get(0);
                Recognition recognition2 = currentRecognitions.get(1);
                telemetry.addData("", recognition1.getConfidence());
                telemetry.addData("", recognition2.getConfidence());
                if (recognition1.getConfidence() > recognition2.getConfidence()) {
                    prop = "recognition 1";
                }

                else {
                    prop = "recognition 2";
                }
            }
            telemetry.addData("", prop);
        }   // end for() loop

    }   // end method telemetryTfod()

}   // end class
