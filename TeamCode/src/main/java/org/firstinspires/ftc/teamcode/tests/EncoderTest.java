package org.firstinspires.ftc.teamcode.tests;

import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.inputs.Camera.visionPortal;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.forwardMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.mapMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.hardcoredMovements.EachMotorSet.drive;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.hardcoredMovements.TargetHardcoreMovements.encoderSpeed;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.hardcoredMovements.TargetHardcoreMovements.ticksPerInch;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.operations.TargetOperations;
import org.firstinspires.ftc.teamcode.operations.Wheels;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Encoders;


@Autonomous(name="Encoder Test", group="test")
// @Disabled // test files are disabled if not in use
public class EncoderTest extends TargetOperations {

    @Override
    public void runOpMode() throws InterruptedException {
        // LinearOpMode that calls a different form of OpMode:
        runInit();
        while(opModeInInit()){runInitLoop();}
        waitForStart();
        runStart();
        if (isStopRequested()){runStop();} // stop OpMode if the button is pressed
        while (opModeIsActive()) {runLoop();}
        visionPortal.close(); // close view for camera
    }

    @Override
    public void runInit() {
        //arm = hardwareMap.dcMotor.get("arm");
        mapMotors(hardwareMap, Wheels.FRONT_LEFT.abbreviation(),Wheels.FRONT_RIGHT.abbreviation(),Wheels.BACK_LEFT.abbreviation(),Wheels.BACK_RIGHT.abbreviation());
        forwardMotors(true,false,true,false);
        // ^ set motor directions
        initAprilTag(hardwareMap, "Webcam 1");
        Encoders.use();
        Encoders.clear();
    }

    @Override
    public void runInitLoop(){}

    @Override
    public void runStart() {
        int ticksPer123Inches = 000; // how many ticks = ___ inches
        forward(1, 1);
    }

    public void forward(double inches, long seconds) {
        int finalTicks = (int) (inches * ticksPerInch);
        Encoders.clear();
        Encoders.target(-finalTicks,-finalTicks,-finalTicks,-finalTicks);
        Encoders.go();
        drive(-encoderSpeed,-encoderSpeed,-encoderSpeed,-encoderSpeed);
        sleep(seconds*1000);
    }

    // - forward here.




    @Override
    public void runStop() {
        stopAll();
    }
    @Override
    public void runLoop() {

        telemetry.addData("position br ", br.getCurrentPosition());
        telemetry.addData("position bl ", bl.getCurrentPosition());
        telemetry.addData("position fr ", fr.getCurrentPosition());
        telemetry.addData("position fl ", fl.getCurrentPosition());
        telemetry.update();
    }
}

