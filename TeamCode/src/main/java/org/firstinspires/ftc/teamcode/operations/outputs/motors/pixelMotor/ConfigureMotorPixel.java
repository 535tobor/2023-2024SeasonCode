package org.firstinspires.ftc.teamcode.operations.outputs.motors.pixelMotor;

import static org.firstinspires.ftc.teamcode.operations.Target_operations.pixelMotorConfig;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.pixelMotor.Target_pixelMotor.*;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class ConfigureMotorPixel {

    public static void mapMotor (HardwareMap hardwareMap, String motorName) {
        pixelMotor = hardwareMap.dcMotor.get(motorName);
        pixelMotorConfig = true;
    }

}
