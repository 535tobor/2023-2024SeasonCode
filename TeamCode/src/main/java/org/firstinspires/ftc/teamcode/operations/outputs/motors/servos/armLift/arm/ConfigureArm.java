package org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.armLift.arm;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.armLift.arm.Target_arm.arm;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class ConfigureArm {

    public static void mapMotor (HardwareMap hardwareMap) {
        arm = hardwareMap.dcMotor.get("arm");
    }

}
