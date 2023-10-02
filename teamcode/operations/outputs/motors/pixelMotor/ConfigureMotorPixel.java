package org.firstinspires.ftc.teamcode.operations.outputs.motors.pixelMotor;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;

public class ConfigureMotorPixel {

    public static DcMotor pixelMotor;

    public static void mapMotor (String motorName) {
        pixelMotor = hardwareMap.dcMotor.get(motorName);
    }

}
