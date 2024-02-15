package org.firstinspires.ftc.teamcode.operations.inputs.oFreeSpin;

import static org.firstinspires.ftc.teamcode.operations.inputs.oFreeSpin.Odometer.oLeft;
import static org.firstinspires.ftc.teamcode.operations.inputs.oFreeSpin.Odometer.oRight;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.driveAuto;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.driveStop;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Target_oFreeSpin {
    public static void oReset() {
        oLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        oRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    
    public static void oTicksGoInches_Backward(double inches) {
        double oneInch = 20_000/12;
        boolean loop = true;
        // 19+3/4 = oLeft -32000 oRight 32000 // 10 14/16
        while (loop) {
            if (oLeft.getCurrentPosition() <= oneInch*inches && oRight.getCurrentPosition() >= -oneInch*inches) {
                driveAuto(-500,-500,-500,-500);
            }
            else {
                driveStop();
                loop = false;
            }
        }
    }

    public static void oTicksGoInches_Forward(int inches) {
        double oneInch = 20_000/12;
        boolean loop = true;
        // 19+3/4 = oLeft -32000 oRight 32000 // 10 14/16
        while (loop) {
            if (oLeft.getCurrentPosition() >= -oneInch*inches && oRight.getCurrentPosition() <= oneInch*inches) {
                driveAuto(-500,-500,-500,-500);
            }
            else {
                driveStop();
                loop = false;
            }
        }
    }
}
