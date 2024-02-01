package org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.gamepad2;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.Target_shaft.shaft;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.Target_claw.claw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.closeClaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.openClaw;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class General {
    public static boolean clawCanMove = true;
    public static void runLoopGamepad2(Gamepad gamepad2) {
        arm.setPower(gamepad2.left_stick_y);
        shaft.setPower(gamepad2.right_stick_y);


        if (gamepad2.left_bumper && clawCanMove) {
            openClaw();
            clawCanMove = false;
        }

        else if (gamepad2.right_bumper && clawCanMove) {
            closeClaw();
            clawCanMove = false;
        }

        else {
            clawCanMove = true;
        }
    }
}