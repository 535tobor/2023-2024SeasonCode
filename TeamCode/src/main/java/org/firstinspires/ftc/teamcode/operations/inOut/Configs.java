package org.firstinspires.ftc.teamcode.operations.inOut;

import static org.firstinspires.ftc.teamcode.gamecode.teleop.RobotCentric.wrist;
import static org.firstinspires.ftc.teamcode.operations.inputs.Odometer.oLeft;
import static org.firstinspires.ftc.teamcode.operations.inputs.Odometer.oRight;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.Target_shaft.shaft;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.Target_claw.claw;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Configs {
    public static DistanceSensor sensorRange;
    public static void mapOtherThings(HardwareMap hardwareMap) {
        sensorRange = hardwareMap.get(DistanceSensor.class, "left_eye");
        wrist = hardwareMap.get(Servo.class, "wrist");
        arm = hardwareMap.get(DcMotorEx.class, "arm");
        shaft = hardwareMap.get(DcMotor.class, "shaft");
        claw = hardwareMap.get(Servo.class, "claw");
        oLeft = hardwareMap.get(DcMotor.class, "oLeft");
        oRight = hardwareMap.get(DcMotor.class, "oRight");
    }
}
