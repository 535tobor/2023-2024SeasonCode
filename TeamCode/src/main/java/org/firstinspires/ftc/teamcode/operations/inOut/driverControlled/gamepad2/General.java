package org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.gamepad2;

import static org.firstinspires.ftc.teamcode.gamecode.teleop.RobotCentric.wrist;
import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.gamepad2.Target_gamepad2.*;
import static org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton.button;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.armMovements.rotateArm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.Target_shaft.shaft;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.Target_claw.claw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.closeClaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.openClaw;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class General {
    public static void runLoopGamepad2(Gamepad gamepad2) {
        shaft.setPower(gamepad2.right_stick_y);

        if (button.isPressed()) {
            arm.setPower(-0.5);
        }
        else {
            arm.setPower(gamepad2.left_stick_y);
        }


        if (gamepad2.left_bumper) {
            closeClaw();
        }

        if (gamepad2.right_bumper) {
            openClaw();
        }



        if (gamepad2.left_trigger > 0) {
            openClaw();
        }

        else if (gamepad2.right_trigger > 0) {
            closeClaw();
        }
    }
}