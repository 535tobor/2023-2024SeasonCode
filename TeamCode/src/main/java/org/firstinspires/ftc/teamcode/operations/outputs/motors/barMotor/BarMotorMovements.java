package org.firstinspires.ftc.teamcode.operations.outputs.motors.barMotor;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.barMotor.Target_barMotor.*;

import com.qualcomm.robotcore.hardware.DcMotor;

public class BarMotorMovements {

        public void motorEncoder () {
            barMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            barMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            barMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        public static void rotate(int rotations, int speed) {
            barMotor.setTargetPosition(rotations);
            barMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            barMotor.setPower(speed);
    }
}
