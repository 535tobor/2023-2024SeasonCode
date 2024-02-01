package org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.armMovements.armStop;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.armMovements.rotateArm;

import com.qualcomm.robotcore.hardware.Gamepad;

public class armButtons {
    public static void armUseWithGamepad(Gamepad gamepad){
        if (gamepad.left_bumper) { // fully close claw
            rotateArm(-3,0.25);
        }
        else {
            armStop();
        }
        if (gamepad.right_bumper) { // fully open claw
            rotateArm(3,0.25);
        }
        else {
            armStop();
        }
    }
}
