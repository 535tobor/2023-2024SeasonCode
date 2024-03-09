package org.firstinspires.ftc.teamcode.operations.inOut;

import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection.UP;
import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection.LEFT;
import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection.RIGHT;
import static org.firstinspires.ftc.teamcode.gamecode.teleop.RobotCentric.wrist;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuGet;
import static org.firstinspires.ftc.teamcode.operations.inputs.oFreeSpin.Odometer.oLeft;
import static org.firstinspires.ftc.teamcode.operations.inputs.oFreeSpin.Odometer.oRight;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.Target_shaft.shaft;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.Target_claw.claw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.hang.SimpleRotateMovements.grabHang;

import android.hardware.Sensor;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.operations.inputs.DeviceNames;
import org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.hang.ConfigureHangReleaseServo;

public class Configs {
    public static DistanceSensor sensorRange;
    public static Sensor drone;
    public static TouchSensor xButton;
    public static void mapOtherThings(HardwareMap hardwareMap) {
        sensorRange = hardwareMap.get(DistanceSensor.class, "left_eye");
        wrist = hardwareMap.get(Servo.class, "wrist");
        arm = hardwareMap.get(DcMotorEx.class, "arm");
        shaft = hardwareMap.get(DcMotor.class, "shaft");
        claw = hardwareMap.get(Servo.class, "claw");
        oLeft = hardwareMap.get(DcMotor.class, "oLeft");
        oRight = hardwareMap.get(DcMotor.class, "oRight");
        xButton = hardwareMap.get(TouchSensor.class, "xButton");

        imuGet(hardwareMap, DeviceNames.DEFAULT_IMU. hardwareMapName(),LEFT.name(), FORWARD.name());
        //drone = hardwareMap.get(Sensor.class, "drone");

        ConfigureHangReleaseServo.mapServo(hardwareMap);

        grabHang(); // set hang release servo position
    }
}
