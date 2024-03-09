package org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.gamepadMovements;

import static android.os.SystemClock.sleep;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.teamcode.operations.inOut.Configs.sensorRange;
import static org.firstinspires.ftc.teamcode.operations.inOut.driverControlled.Target_driverControlled.isFieldCentric;
import static org.firstinspires.ftc.teamcode.operations.inputs.Imu.imuReset;
import static org.firstinspires.ftc.teamcode.operations.inputs.TouchSensorButton.button;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.Target_arm.arm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.arm.armMovements.rotateArm;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.armLift.shaft.Target_shaft.shaft;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.dpadMovements;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.extraSpeed;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.fieldCentricMath;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Mecanum.robotCentricMath;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.backLeftPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.backRightPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.frontLeftPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Target_drive.frontRightPower;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.drive;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EachMotorSet.useDriveEncoders;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.forwardAutoKeepGo;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.forwardAutoKeepGoTeleOp;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.forwardAutoTeleOp;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.closeClaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.claw.clawMovements.openClaw;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.doneDown;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.doneUp;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.wristDownTeleOp;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.wristIn;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.wristMove;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.wristOut;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.clawWrist.wristClawMovements.wristUpTeleOp;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.servos.hang.SimpleRotateMovements.releaseHang;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.operations.outputs.driverStation.TelemetryShow;

public class General {
    static boolean buttonXHeld = false;
    static boolean notDone = true;
    static boolean canIplaceAgain = true;
    public static Servo drone;
    public static boolean hasBeenPressedHere = false;
    public static void generalControlSetup() {
        wristOut(); // out is claw wrist down
        arm.setPower(1);
        if (button.isPressed() && !hasBeenPressedHere) {
            arm.setPower(-1);
            // move arm up only one notch
            // open claw so the robot fits in the 18x18 zone
            openClaw();
            hasBeenPressedHere = true;
        }
        else if (hasBeenPressedHere) {
            arm.setPower(0);
        }
        else {
            arm.setPower(1);
        }
    }
    public static void useArmEncoders(boolean encoders) {
        if (encoders) {
            arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            shaft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        else {
            arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            shaft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }
    public static void boardPlaceRunHigh(Telemetry telemetry) {
        useDriveEncoders(true);
        useArmEncoders(true);
        rotateArm(4122,1);
        sleep(5000);
        shaft.setTargetPosition(-599);
        shaft.setPower(1);
        shaft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wristMove(0.24); // closest to 0


        while (sensorRange.getDistance(DistanceUnit.INCH) > 5) {
            forwardAutoKeepGoTeleOp(1,1000);
            telemetry.addData("forward", "Go!");
            telemetry.addData("distance", sensorRange.getDistance(DistanceUnit.INCH));
            telemetry.addData("distance", sensorRange.getDistance(DistanceUnit.INCH));
            telemetry.update();
        }
        openClaw();


        useDriveEncoders(false);
        useArmEncoders(false);
    }
    public static void boardPlaceRun(Telemetry telemetry) {
        useDriveEncoders(true);
        useArmEncoders(true);
        rotateArm(4122,1);
        sleep(5000);
        shaft.setTargetPosition(-599);
        shaft.setPower(1);
        shaft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wristMove(0.24); // closest to 0


        while (sensorRange.getDistance(DistanceUnit.INCH) > 5) {
            forwardAutoKeepGoTeleOp(1,1000);
            telemetry.addData("forward", "Go!");
            telemetry.addData("distance", sensorRange.getDistance(DistanceUnit.INCH));
            telemetry.addData("distance", sensorRange.getDistance(DistanceUnit.INCH));
            telemetry.update();
        }
        openClaw();


        useDriveEncoders(false);
        useArmEncoders(false);
    }
    public static void driveMovements(Gamepad gamepad1, Gamepad gamepad2, Telemetry telemetry, double speed) {
        dpadMovements(gamepad1, gamepad2, speed); // sets waypoints to the d_pads's positions
        extraSpeed(gamepad1);

        imuReset(gamepad1.options); // resets imu case of accidents or incidences
        if (isFieldCentric) {
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
        if (gamepad2.y && gamepad2.back) {
            forwardAutoTeleOp(2, 1,2000);
        }
        else {
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
                        if (!doneUp) {
                            wristUpTeleOp(); // claw wrist up
                        }
                    }

                    else if (gamepad2.dpad_down) {
                        if (!doneDown) {
                            wristDownTeleOp(); // claw wrist down
                        }
                    }

                    else if (gamepad2.dpad_left) {
                        wristMove(0.7);
                    }

                    else if (gamepad2.dpad_right) {
                        wristMove(0.4);
                    }

                    else {
                        doneUp = false;
                        doneDown = false;
                        shaft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                        shaft.setPower(gamepad2.right_stick_y);
                        if (gamepad2.right_stick_y == 0) {
                            shaft.setZeroPowerBehavior(BRAKE);
                        }
                        driveMovements(gamepad1, gamepad2, telemetry, speed);
                    }
                }


            }




            if (button.isPressed()) {
                arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                arm.setPower(-0.5);
            }
            else {
                arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                arm.setPower(gamepad2.left_stick_y);
            }
        }

    }
    public static void runLoopGamepad2(Telemetry telemetry, Gamepad gamepad2, Gamepad gamepad1, double speed) {
        if (gamepad1.x || gamepad2.x) {
            if (gamepad1.y || gamepad2.y) {
                boardPlaceRunHigh(telemetry);
                canIplaceAgain = false;
            }
            if (canIplaceAgain) {
                boardPlaceRun(telemetry);
                canIplaceAgain = false;
            }
        }
        else {
            canIplaceAgain = true;
            // Button X is released
            //telemetry.addData("Status", "Button X released");
            //buttonXHeld = false;
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