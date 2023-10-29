package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.*;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Encoders {
    public static void target(int frontLeft, int frontRight, int backLeft, int backRight){
        fl.setTargetPosition(frontLeft);
        fr.setTargetPosition(frontRight);
        bl.setTargetPosition(backLeft);
        br.setTargetPosition(backRight);
    }
    public static void go() {
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public static void clear() {
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public static void use() {
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
