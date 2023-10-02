package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class configureMotors {

    public static DcMotor fr;
    public static DcMotor fl;
    public static DcMotor br;
    public static DcMotor bl;

    static HardwareMap hardwareMap = null;

    public static void mapMotors (String frontLeftMotor, String frontRightMotor, String backLeftMotor, String backRightMotor) {
        fr = hardwareMap.dcMotor.get(frontRightMotor);
        fl = hardwareMap.dcMotor.get(frontLeftMotor);
        br = hardwareMap.dcMotor.get(backRightMotor);
        bl = hardwareMap.dcMotor.get(backLeftMotor);
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
