package org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist;


import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.Target_claw.wrist;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class ConfigureWristClaw {

    public static void mapServo (HardwareMap hardwareMap) {

        wrist = hardwareMap.servo.get("wrist");
    }

}
