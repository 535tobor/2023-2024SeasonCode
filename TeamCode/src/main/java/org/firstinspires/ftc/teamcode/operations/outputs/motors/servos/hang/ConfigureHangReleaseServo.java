package org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.hang;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.hang.Target_hang.*;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class ConfigureHangReleaseServo {

    public static void mapServo (HardwareMap hardwareMap) {

        hang = hardwareMap.servo.get("hang");
    }

}
