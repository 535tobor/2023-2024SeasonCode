package org.firstinspires.ftc.teamcode.operations.outputs.motors.barMotor;

import static org.firstinspires.ftc.teamcode.operations.TargetOperations.barMotorConfig;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ConfigureMotorBar {

    public static DcMotor barMotor;
    public static void mapMotor (HardwareMap hardwareMap, String motorName) {
        barMotor = hardwareMap.dcMotor.get(motorName);
        barMotorConfig = true;
    }

}
