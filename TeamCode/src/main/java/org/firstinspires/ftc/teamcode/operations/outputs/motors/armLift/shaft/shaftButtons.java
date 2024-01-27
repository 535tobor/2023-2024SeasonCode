package org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.shaftMovements.moveShaft;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.shaftMovements.shaftStop;

import com.qualcomm.robotcore.hardware.Gamepad;

public class shaftButtons {
    public static void shaftUseWithGamepad(Gamepad gamepad){
        if (gamepad.dpad_up) { // fully close claw
            moveShaft(3,0.25);
        }
        else {
            shaftStop();
        }
        if (gamepad.dpad_down) { // fully open claw
            moveShaft(-3,0.25);
        }
        else {
            shaftStop();
        }
    }
}
