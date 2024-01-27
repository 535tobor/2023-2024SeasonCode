package org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.armLift.arm;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.armLift.arm.armMovements.armStop;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.armLift.arm.armMovements.rotateArm;

import com.qualcomm.robotcore.hardware.Gamepad;

public class rotateButtons {
    private static int armRotation = 0;
    public static void armUseWithGamepad(Gamepad gamepad){
        if (gamepad.left_bumper) {
            armRotation -= 1;
            rotateArm(armRotation,0.5);
        }
        else {
            armStop();
        }
        if (gamepad.right_bumper) {
            armRotation += 1;
            rotateArm(armRotation,0.5);
        }
        else {
            armStop();
        }

    }
}
