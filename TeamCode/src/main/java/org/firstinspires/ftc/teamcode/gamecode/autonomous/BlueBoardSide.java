package org.firstinspires.ftc.teamcode.gamecode.autonomous;

import static android.os.SystemClock.sleep;
import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection.UP;
import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection.RIGHT;
import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.RobotCentric.sensorRange;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuGet;
import static org.firstinspires.ftc.teamcode.operations.inputs.Target_inputs.imu;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.armMovements.rotateArm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.Target_shaft.shaft;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.forwardMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.mapMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.drive;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.driveStop;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.backwardAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.forwardAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.strafeLeftAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.strafeRightAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.turnLeftAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.turnRightAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.Target_definingDriveMovements.ticksPerInch;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.Target_claw.claw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.closeClaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.openClaw;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.operations.Target_operations;
import org.firstinspires.ftc.teamcode.operations.inputs.DeviceNames;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Encoders;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Wheels;

@Autonomous(name="Blue, Board Side", group="auto")
public class BlueBoardSide extends Target_operations {
    Orientation direction;
    private static int teamprop;
    // and save the heading
    double botHeading;
    double botTargetHeading;

    @Override
    public void runOpMode() throws InterruptedException {
        // LinearOpMode that calls a different form of OpMode:
        runInit();
        while (opModeInInit()) {
            runInitLoop();
        }
        waitForStart();
        runStart();
        if (isStopRequested()) {
            runStop();
        } // stop OpMode if the button is pressed
        while (opModeIsActive()) {
            runLoop();
        }
    }

    @Override
    public void runInit() {
        //arm = hardwareMap.dcMotor.get("arm");
        mapMotors(hardwareMap, Wheels.FRONT_LEFT.abbreviation(), Wheels.FRONT_RIGHT.abbreviation(), Wheels.BACK_LEFT.abbreviation(), Wheels.BACK_RIGHT.abbreviation());
        forwardMotors(true, false, true, false);
        imuGet(hardwareMap, DeviceNames.DEFAULT_IMU.hardwareMapName(), RIGHT.name(), UP.name());
        // ^ set motor directions
        initAprilTag(hardwareMap, "Webcam 1", telemetry);
        Encoders.clear();

        sensorRange = hardwareMap.get(DistanceSensor.class, "left_eye");

        arm = hardwareMap.get(DcMotorEx.class, "extend");
        shaft = hardwareMap.get(DcMotor.class, "shaft");
        claw = hardwareMap.get(Servo.class, "claw");


    }

    @Override
    public void runInitLoop() {
        openClaw();
    }

    @Override
    public void runStart() {

        closeClaw(); // close
        sleep(2000);
        // guess that team prop is in middle
        rotateArm(-200, -20);
        sleep(2000);


        forwardAuto(26, 5, 800);

        if (sensorRange.getDistance(DistanceUnit.INCH) < 9) {
            teamprop = 2;
            //openClaw();
            //sleep(10000);
            //closeClaw();
            backwardAuto(28, 2, 800);
            strafeLeftAuto(40, 5, 800);
            keepGoing();
        } else {
            strafeRightAuto(2, 1, 500);
            if (sensorRange.getDistance(DistanceUnit.INCH) < 9) {
                teamprop = 2;
                //openClaw();
                //sleep(2000);
                //closeClaw();
                backwardAuto(28, 2, 800);
                strafeLeftAuto(40, 5, 800);
                keepGoing();
            } else {
                turnRightAuto(500 * 3, 2, 500);
                if (sensorRange.getDistance(DistanceUnit.INCH) < 9) {
                    teamprop = 3;
                    //openClaw();
                    sleep(2000);
                    //closeClaw();
                    backwardAuto(38, 5, 500);
                } else {
                    turnLeftAuto((500 * 3) * 2, 2, 500); // 180 degrees (about)
                    // no need to check due to process of elimination.
                    teamprop = 1;
                    //openClaw();
                    sleep(2000);
                    //closeClaw();
                    strafeLeftAuto(10, 5, 500);
                    forwardAuto(40, 5, 500);
                }
            }
        }

            /*turnRightAuto(500*3,2,500); // 90 degrees
            backwardAuto(8,1,500);
            strafeRightAuto(5,1,500);
            turnRightAuto(500*3/2,2,500); // 45 degrees

            if (sensorRange.getDistance((DistanceUnit.INCH)) < 10) {
                forwardAuto(2,1,500);
                openClaw();
            }*/
    }
    /* WARNING!!!!

    arm is being raised to high, check data.
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    * */

    public static void keepGoing() {
        if (teamprop == 2) {
            forwardAuto(25,2,800);
            turnLeftAuto(500*3,2,500);
            backwardAuto(8,2,500);

            if (sensorRange.getDistance(DistanceUnit.INCH) < 9) {
                backwardAuto(2,2,500);
            }
            else if (sensorRange.getDistance(DistanceUnit.INCH) > 12) {
                forwardAuto(2,2,500);
            }
        }
    }

    @Override
    public void runStop() {
        stopAll();
    }
    @Override
    public void runLoop() {
        telemetry.addData("position FL ", fl.getCurrentPosition());
        telemetry.addData("position FR ", fr.getCurrentPosition());
        telemetry.addData("position BL ", bl.getCurrentPosition());
        telemetry.addData("position BR ", br.getCurrentPosition());
        telemetry.addData("TargetTicksPerInch (final) : ", 30 * ticksPerInch);
        telemetry.addData("Sensor Range (Inches) : ", sensorRange.getDistance(DistanceUnit.INCH));
        telemetry.addData("Bot Heading : ", sensorRange.getDistance(DistanceUnit.INCH));
        telemetry.addData("Bot Heading Final : ", sensorRange.getDistance(DistanceUnit.INCH));
        telemetry.update();
    }
}

