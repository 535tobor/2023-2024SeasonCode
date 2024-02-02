package org.firstinspires.ftc.teamcode.operations.inputs;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class TouchSensorButton {
    public static TouchSensor button;
    public static void mapDigital (HardwareMap hardwareMap) {
        button = hardwareMap.get(TouchSensor.class, "button");
    }
}
