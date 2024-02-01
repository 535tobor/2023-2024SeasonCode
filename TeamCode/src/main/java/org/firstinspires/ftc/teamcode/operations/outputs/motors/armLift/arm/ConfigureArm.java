package org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ConfigureArm {

    public static void mapMotor (HardwareMap hardwareMap) {
        arm = (DcMotorEx) hardwareMap.dcMotor.get("extend");
    }

}
