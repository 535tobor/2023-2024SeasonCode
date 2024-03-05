package org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.gamepadMovements;

import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.Target_driverControlled.fieldCentric;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuReset;
import static org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton.button;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.dpadMovements;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.extraSpeed;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.fieldCentricMath;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.robotCentricMath;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.backLeftPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.backRightPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.frontLeftPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.frontRightPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.drive;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.closeClaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.openClaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.wristIn;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.wristMove;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.wristOut;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.hang.SimpleRotateMovements.releaseHang;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.operations.outputs.driverStation.TelemetryShow;

public class General {
    static boolean buttonXHeld = false;
    public static Servo drone;
    public static void boardPlaceRun() {

    }
    public static void driveMovements(Gamepad gamepad1, Telemetry telemetry, double speed) {
        dpadMovements(gamepad1, speed); // sets waypoints to the d_pads's positions
        extraSpeed(gamepad1);


        imuReset(gamepad1.options); // resets imu case of accidents or incidences
        if (fieldCentric) {
            fieldCentricMath(); // does the required math for Mecanum drive (Robot Centric)
        }
        else {
            robotCentricMath(); // does the required math for Mecanum drive (Robot Centric)
        }

        TelemetryShow.allLoopMessages(telemetry);


        drive(frontLeftPower,frontRightPower,backLeftPower,backRightPower);
        // sets each motor to the encoder counts given by the waypoints method
    }
    public static void moreMovements(Gamepad gamepad1, Gamepad gamepad2, Telemetry telemetry, double speed) {
        if (gamepad2.back && gamepad2.start && gamepad1.back && gamepad1.start) {
            releaseHang();
        }

        else {
            // claw
            if (gamepad2.left_bumper) {
                openClaw();
            }

            else if (gamepad2.right_bumper) {
                closeClaw();
            }

            else {
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

                else {
                    driveMovements(gamepad1, telemetry, speed);
                }
            }


        }




        if (button.isPressed()) {
            arm.setPower(-0.5);
        }
        else {
            arm.setPower(gamepad2.left_stick_y);
        }
    }
    public static void runLoopGamepad2(Telemetry telemetry, Gamepad gamepad2, Gamepad gamepad1, double speed) {
        if (gamepad1.x) {
            if (!buttonXHeld) {
                // Button A was just pressed
                telemetry.addData("Status", "Button A pressed");
                buttonXHeld = true;
            } else {
                // Button A is held
                telemetry.addData("Status", "Button A held");
                boardPlaceRun();
            }
        } else {
            // Button A is released
            telemetry.addData("Status", "Button A released");
            buttonXHeld = false;
            moreMovements(gamepad1, gamepad2, telemetry, speed);
        }

        /*if (gamepad2.a) {
            drone.setPosition(1);
        }

        if (gamepad2.b) {
            drone.setPosition(0);
        }*/
    }
}