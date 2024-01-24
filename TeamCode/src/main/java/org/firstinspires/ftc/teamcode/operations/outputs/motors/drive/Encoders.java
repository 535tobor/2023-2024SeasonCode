package org.firstinspires.ftc.teamcode.operations.outputs.motors.drive;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Encoders {
    public static void target(int frontLeft, int frontRight, int backLeft, int backRight){
        fl.setTargetPosition(frontLeft);
        fr.setTargetPosition(frontRight);
        bl.setTargetPosition(backLeft);
        br.setTargetPosition(backRight);
        // set target position for all 4 motors individually
    }

    public static void targetAll(int position){
        fl.setTargetPosition(position);
        fr.setTargetPosition(position);
        bl.setTargetPosition(position);
        br.setTargetPosition(position);
        // set target position for all 4 motors with one number
    }
    public static void go() {
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // make all 4 motors run to the set position
    }

    public static void clear() {
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // resets all 4 drive encoders
    }

    public static void use() {
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // uses all 4 encoders
    }
}
