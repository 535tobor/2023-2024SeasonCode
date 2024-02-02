package org.firstinspires.ftc.teamcode.operations.inOut.driverControlled;

import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection.UP;
import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection.RIGHT;
import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.RobotCentric.sensorRange;
import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.gamepad2.General.runLoopGamepad2;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuGet;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuReset;
import static org.firstinspires.ftc.teamcode.operations.inputs.Target_inputs.imu;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.Target_shaft.shaft;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.forwardMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.mapMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.botHeading;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.dpadMovements;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.fieldCentricMath;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.backLeftPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.backRightPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.frontLeftPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.frontRightPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.drive;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.Target_claw.claw;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.operations.inputs.DeviceNames;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.ConfigureArm;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.ConfigureShaft;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.ConfigureClaw;

public class FieldCentric {
    public static void initFieldCentric(HardwareMap hardwareMap, Telemetry telemetry) {
        mapMotors(hardwareMap, "fl","fr","bl","br");
        ConfigureArm.mapMotor(hardwareMap);
        ConfigureClaw.mapServo(hardwareMap);
        ConfigureShaft.mapMotor(hardwareMap);
        forwardMotors(false,true,false,true);
        imuGet(hardwareMap, DeviceNames.DEFAULT_IMU.hardwareMapName(), RIGHT.name(), UP.name());
        initAprilTag(hardwareMap, DeviceNames.DEFAULT_CAMERA.hardwareMapName(), telemetry);
        sensorRange = hardwareMap.get(DistanceSensor.class, "left_eye");

        arm = hardwareMap.get(DcMotorEx.class, "arm");
        shaft = hardwareMap.get(DcMotor.class, "shaft");
        claw = hardwareMap.get(Servo.class, "claw");
    }

    public static void runLoopFieldCentric(Telemetry telemetry, Gamepad gamepad1, Gamepad gamepad2, double speed){
        telemetry.addData("claw: ",claw.getPosition());
        telemetry.addData("Distance in Inches: ",sensorRange.getDistance(DistanceUnit.INCH));

        dpadMovements(gamepad1, speed); // sets waypoints to the d_pads's positions


        imuReset(gamepad1.options); // resets imu case of accidents or incidences
        fieldCentricMath(); // does the required math for Mecanum drive as well as getting imu for field centric

        telemetry.addData("bot heading: ", botHeading);
        runLoopGamepad2(gamepad2);



        telemetry.addData("position FL ", fl.getCurrentPosition());
        telemetry.addData("position FR ", fr.getCurrentPosition());
        telemetry.addData("position BL ", bl.getCurrentPosition());
        telemetry.addData("position BR ", br.getCurrentPosition());
        telemetry.addData("degrees ", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.addData("arm", arm.getCurrentPosition());
        telemetry.addData("shaft", arm.getCurrentPosition());
        telemetry.update();


        //clawUseWithGamepad(gamepad2); // using the claw (open/close)
        //armUseWithGamepad(gamepad2); // using the arm (rotation)
        //shaftUseWithGamepad(gamepad2); // using the shaft/lift (up/down)
        drive(frontLeftPower,frontRightPower,backLeftPower,backRightPower);
        // sets each motor to the encoder counts given by the waypoints method
    }
}
