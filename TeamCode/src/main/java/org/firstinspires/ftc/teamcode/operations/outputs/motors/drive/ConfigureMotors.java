package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ConfigureMotors {

    public static DcMotorEx fr;
    public static DcMotorEx fl;
    public static DcMotorEx br;
    public static DcMotorEx bl;

    public static double y;
    public static double x;
    public static double rx;

    // Get an instance of HardwareMap


    public static void mapMotors (HardwareMap mapHardware, String frontLeftMotor, String frontRightMotor, String backLeftMotor, String backRightMotor){
        fl = (DcMotorEx) mapHardware.dcMotor.get(frontLeftMotor);
        fr = (DcMotorEx) mapHardware.dcMotor.get(frontRightMotor);
        bl = (DcMotorEx) mapHardware.dcMotor.get(backLeftMotor);
        br = (DcMotorEx) mapHardware.dcMotor.get(backRightMotor);


    }


    public static void forwardMotors (boolean frontLeftMotor, boolean frontRightMotor, boolean backLeftMotor, boolean backRightMotor) {

        if (!frontRightMotor) {
            fr.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if (!frontLeftMotor) {
            fl.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if (!backRightMotor) {
            br.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if (!backLeftMotor) {
            bl.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }

}
