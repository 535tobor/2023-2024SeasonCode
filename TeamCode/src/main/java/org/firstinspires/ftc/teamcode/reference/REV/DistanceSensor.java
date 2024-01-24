package org.firstinspires.ftc.teamcode.reference.REV;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp
@Disabled
public class DistanceSensor extends LinearOpMode {
    DistanceSensor distance;
    DcMotor motor;

    @Override
    public void runOpMode() {
        // Get the distance sensor and motor from hardwareMap
        distance = hardwareMap.get(DistanceSensor.class, "Distance");
        motor = hardwareMap.get(DcMotor.class, "Motor");


        // Loop while the Op Mode is running
        waitForStart();
        while (opModeIsActive()) {

        }
    }
}