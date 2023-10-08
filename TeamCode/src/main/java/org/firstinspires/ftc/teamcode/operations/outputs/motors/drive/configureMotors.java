package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive;

import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imu;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class configureMotors {

    public static DcMotor fr;
    public static DcMotor fl;
    public static DcMotor br;
    public static DcMotor bl;

    public static double y;
    public static double x;
    public static double rx;

    // Get an instance of HardwareMap


    public static void mapMotors (HardwareMap mapHardware, String frontLeftMotor, String frontRightMotor, String backLeftMotor, String backRightMotor){
        fl = mapHardware.dcMotor.get(frontLeftMotor);
        fr = mapHardware.dcMotor.get(frontRightMotor);
        bl = mapHardware.dcMotor.get(backLeftMotor);
        br = mapHardware.dcMotor.get(backRightMotor);
    }


    public static void forwardMotors (boolean frontLeftMotor, boolean frontRightMotor, boolean backLeftMotor, boolean backRightMotor) {

        if (frontRightMotor) {
            fr.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        else {
            fr.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if (frontLeftMotor) {
            fl.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        else {
            fl.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if (backRightMotor) {
            br.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        else {
            br.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if (backLeftMotor) {
            bl.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        else {
            bl.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }

}
