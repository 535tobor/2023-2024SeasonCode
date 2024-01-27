package org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class ConfigureArm {

    public static void mapMotor (HardwareMap hardwareMap) {
        Target_arm.arm = hardwareMap.dcMotor.get("arm");
    }

}
