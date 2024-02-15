package org.firstinspires.ftc.teamcode.gamecode.tests;

import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection.UP;
import static org.firstinspires.ftc.teamcode.operations.inOut.Configs.mapOtherThings;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.visionPortal;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuGet;
import static org.firstinspires.ftc.teamcode.operations.inputs.Target_inputs.imu;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.armMovements.rotateArm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.forwardMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.mapMotors;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.operations.Target_operations;
import org.firstinspires.ftc.teamcode.operations.inputs.DeviceNames;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Encoders;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Wheels;


@Autonomous(name="Encoder Test", group="test")
// @Disabled // test files are disabled if not in use
public class EncoderTest extends Target_operations {

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
        mapMotors(hardwareMap, Wheels.FRONT_LEFT.abbreviation(), Wheels.FRONT_RIGHT.abbreviation(), Wheels.BACK_LEFT.abbreviation(), Wheels.BACK_RIGHT.abbreviation());
        forwardMotors(true,false,true,false);
        // ^ set motor directions
        initAprilTag(hardwareMap, "Webcam 1", telemetry);
        imuGet(hardwareMap, DeviceNames.DEFAULT_IMU.hardwareMapName(), LEFT.name(), UP.name());
        Encoders.clear();

        mapOtherThings(hardwareMap);
    }

    @Override
    public void runInitLoop(){}

    @Override
    public void runStart() {
        // move the robot a somewhat random tick about to see how many ticks is in some random inches
        // if it is not whole that is fine.
        // here is the equation: ticksPerInches / inches = ticksPerOnceInch
        // example if I have 5,000 ticks in 3 and 2/16 of and inch then I do 5,000 / 3 2/16 = 1600 ticks in one inch
        // for measuring angles I will have to work with a specific angel though, such as 90 degrees, 180, or 360
        rotateArm(-200,-2);

    } // 150 degrees



    @Override
    public void runStop() {
        stopAll();
    }
    @Override
    public void runLoop() {


        Orientation orientation = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.YZX, AngleUnit.DEGREES);
        telemetry.addData("position br ", orientation);

        telemetry.addData("position br ", br.getCurrentPosition());
        telemetry.addData("position bl ", bl.getCurrentPosition());
        telemetry.addData("position fr ", fr.getCurrentPosition());
        telemetry.addData("position fl ", fl.getCurrentPosition());
        telemetry.update();
    }
}

