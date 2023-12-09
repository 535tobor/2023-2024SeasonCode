package org.firstinspires.ftc.teamcode.operations.outputs.motors.barMotor;

import static org.firstinspires.ftc.teamcode.operations.Target_operations.barMotorConfig;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.barMotor.Target_barMotor.barMotor;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class ConfigureMotorBar {
    public static void mapMotor (HardwareMap hardwareMap, String motorName) {
        barMotor = hardwareMap.dcMotor.get(motorName);
        barMotorConfig = true;
    }

}
