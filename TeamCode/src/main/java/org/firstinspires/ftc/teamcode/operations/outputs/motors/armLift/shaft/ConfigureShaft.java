package org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.Target_shaft.shaft;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class ConfigureShaft {

    public static void mapMotor (HardwareMap hardwareMap) {
        shaft = hardwareMap.dcMotor.get("shaft");

    }

}
