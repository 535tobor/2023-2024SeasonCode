package org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.gamepad2;

import static org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton.button;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.Target_shaft.shaft;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.closeClaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.openClaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.wristIn;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.wristMove;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.wristOut;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.hang.SimpleRotateMovements.releaseHang;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class General {
    public static Servo drone;
    public static void runLoopGamepad2(Gamepad gamepad2, Gamepad gamepad1) {
        // shaft
        shaft.setPower(gamepad2.right_stick_y);
        /*if (gamepad2.a) {
            drone.setPosition(1);
        }

        if (gamepad2.b) {
            drone.setPosition(0);
        }*/

        if (gamepad2.back && gamepad2.start && gamepad1.back && gamepad1.start) {
            releaseHang();
        }


        // arm
        if (button.isPressed()) {
            arm.setPower(-0.5);
        }
        else {
            arm.setPower(gamepad2.left_stick_y);
        }



        // claw
        if (gamepad2.left_bumper) {
            openClaw();
        }

        if (gamepad2.right_bumper) {
            closeClaw();
        }



        // wrist
        if (gamepad2.dpad_up) {
            wristOut();
        }

        else if (gamepad2.dpad_down) {
            wristIn();
        }

        else if (gamepad2.dpad_left) {
            wristMove(0.7);
        }

        else if (gamepad2.dpad_right) {
            wristMove(0.4);
        }
    }
}