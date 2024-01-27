package org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class ConfigureShaft {

    public static void mapMotor (HardwareMap hardwareMap) {
        Target_shaft.shaft = hardwareMap.dcMotor.get("shaft");
    }

}
