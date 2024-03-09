package org.firstinspires.ftc.teamcode.operations.inOut.driverControlled;

import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection.UP;
import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection.RIGHT;
import static org.firstinspires.ftc.teamcode.operations.inOut.Configs.mapOtherThings;
import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.Target_driverControlled.isFieldCentric;
import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.gamepadMovements.General.generalControlSetup;
import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.gamepadMovements.General.runLoopGamepad2;
import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuGet;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuReset;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.Target_shaft.shaft;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.forwardMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.mapMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.dpadMovements;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.extraSpeed;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.robotCentricMath;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.backLeftPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.backRightPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.frontLeftPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.frontRightPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.operations.inputs.DeviceNames;
import org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton;
import org.firstinspires.ftc.teamcode.operations.outputs.driverStation.TelemetryShow;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.ConfigureArm;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.ConfigureShaft;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.ConfigureClaw;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.ConfigureWristClaw;

public class RobotCentric {
    public static void initRobotCentric(HardwareMap hardwareMap, Telemetry telemetry) {
        mapMotors(hardwareMap, "fl","fr","bl","br");

        ConfigureArm.mapMotor(hardwareMap);
        ConfigureClaw.mapServo(hardwareMap);
        ConfigureWristClaw.mapServo(hardwareMap);
        ConfigureShaft.mapMotor(hardwareMap);

        forwardMotors(false,true,false,true);
        // imu is in mapOtherThings
        initAprilTag(hardwareMap, DeviceNames.DEFAULT_CAMERA.hardwareMapName(), telemetry);

        mapOtherThings(hardwareMap);
        TouchSensorButton.mapDigital(hardwareMap); // button

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shaft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        isFieldCentric = false;
    }

    public static void runLoopRobotCentric(Telemetry telemetry, Gamepad gamepad1, Gamepad gamepad2, double speed){
        /*dpadMovements(gamepad1, speed); // sets waypoints to the d_pads's positions
        extraSpeed(gamepad1);


        imuReset(gamepad1.options); // resets imu case of accidents or incidences
        robotCentricMath(); // does the required math for Mecanum drive (Robot Centric)*/

        runLoopGamepad2(telemetry, gamepad2, gamepad1, speed);
        TelemetryShow.allLoopMessages(telemetry);
        /*


        //clawUseWithGamepad(gamepad2); // using the claw (open/close)
        //armUseWithGamepad(gamepad2); // using the arm (rotation)
        //shaftUseWithGamepad(gamepad2); // using the shaft/lift (up/down)
        drive(frontLeftPower,frontRightPower,backLeftPower,backRightPower);
        // sets each motor to the encoder counts given by the waypoints method*/
    }
}
