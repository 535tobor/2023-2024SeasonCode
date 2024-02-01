package org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw;


import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.Target_claw.*;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ConfigureClaw {

    public static void mapServo (HardwareMap hardwareMap) {

        claw = hardwareMap.servo.get("claw");
    }

}
