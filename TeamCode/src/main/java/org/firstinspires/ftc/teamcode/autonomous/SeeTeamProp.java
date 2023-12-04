package org.firstinspires.ftc.teamcode.autonomous;

import static org.firstinspires.ftc.teamcode.operations.inputs.TeamProps.initFlow;
import static org.firstinspires.ftc.teamcode.operations.inputs.TeamProps.propFound;
import static org.firstinspires.ftc.teamcode.operations.inputs.TeamProps.runFlow;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.forwardMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.mapMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.hardcoredMovements.EachMotorSet.drive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.operations.TargetOperations;
import org.firstinspires.ftc.teamcode.operations.Wheels;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Encoders;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;
@Autonomous(name="See Team Prop", group="auto")
public class SeeTeamProp extends TargetOperations {

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
        runInit();
        waitForStart();
        // forward
        Encoders.clear();
        Encoders.target(-4000,-4000,-4000,-4000);
        Encoders.go();
        drive(-500,-500,-500,-500);
        sleep(1200);

        // - is forward, I can change that later
        // turn Left so that the robot looks at the team prop on left tape
        Encoders.clear();
        Encoders.target(200,-200,200,-200);
        Encoders.go();
        drive(500,-500,500,-500);
        stop();
        sleep(500);


        if (opModeIsActive()) {
            while (opModeIsActive()) {

                runFlow(gamepad1, telemetry);

                // Share the CPU.
                sleep(20);

                if (propFound) {
                    // turn right
                    Encoders.clear();
                    Encoders.target(-200,200,-200,200);
                    Encoders.go();
                    drive(-500,500,-500,500);
                    stop();
                    sleep(500);

                    // back up
                    Encoders.clear();
                    Encoders.target(4000,4000,4000,4000);
                    Encoders.go();
                    drive(500,500,500,500);
                    sleep(1200);
                }
            }
        }

        // Save more CPU resources when camera is no longer needed.
        visionPortal.close();

    }   // end runOpMode()

    @Override
    public void runInit() {
        //arm = hardwareMap.dcMotor.get("arm");
        mapMotors(hardwareMap, Wheels.FRONT_LEFT.abbreviation(),Wheels.FRONT_RIGHT.abbreviation(),Wheels.BACK_LEFT.abbreviation(),Wheels.BACK_RIGHT.abbreviation());
        forwardMotors(true,false,true,false);
        // ^ set motor directions
        //initAprilTag(hardwareMap, "Webcam 1");
        initFlow(hardwareMap, "Webcam 1");

        // Wait for the DS start button to be touched.
        telemetry.addData("DS preview on/off", "3 dots, Camera Stream");
        telemetry.addData(">", "Touch Play to start OpMode");
        telemetry.update();
    }

    @Override
    public void runInitLoop() {

    }

    @Override
    public void runStart() {

    }

    @Override
    public void runLoop() {

    }

    @Override
    public void runStop() {

    }
}