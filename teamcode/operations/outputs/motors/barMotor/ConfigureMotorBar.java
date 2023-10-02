package org.firstinspires.ftc.teamcode.operations.outputs.motors.barMotor;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;

public class ConfigureMotorBar {

    public static DcMotor barMotor;
    public static void mapMotor (String motorName) {
        barMotor = hardwareMap.dcMotor.get(motorName);
    }

}
