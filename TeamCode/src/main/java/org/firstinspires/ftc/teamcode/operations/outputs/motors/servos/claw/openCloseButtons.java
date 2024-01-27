package org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.*;
import com.qualcomm.robotcore.hardware.Gamepad;

public class openCloseButtons {
    public static void clawUseWithGamepad(Gamepad gamepad){
        if (gamepad.x) { // fully close claw
            closeClawSet();
        }
        if (gamepad.b) { // fully open claw
            openClawSet();
        }
        if (gamepad.y) { // gradually open claw
            openClawAdd();
        }
        if (gamepad.a) { // gradually close claw
            closeClawAdd();
        }
    }
}
