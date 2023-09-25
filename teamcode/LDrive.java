package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public abstract class LDrive extends LinearOpMode {
    DcMotor fr;
    DcMotor fl;
    DcMotor br;
    DcMotor bl;
    IMU imu;
    public void mapMotors (String frontRightMotor, String frontLeftMotor, String backRightMotor, String backLeftMotor) {
        fr = hardwareMap.dcMotor.get(frontLeftMotor);
        fl = hardwareMap.dcMotor.get(frontRightMotor);
        br = hardwareMap.dcMotor.get(backRightMotor);
        bl = hardwareMap.dcMotor.get(backLeftMotor);
    }

    public void forwardMotors (boolean frontRightMotor, boolean frontLeftMotor, boolean backRightMotor, boolean backLeftMotor) {

        if (frontRightMotor) {
            fr.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        else {
            fr.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if (frontLeftMotor) {
            fl.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        else {
            fl.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if (backRightMotor) {
            br.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        else {
            br.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if (backLeftMotor) {
            bl.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        else {
            bl.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }

    public void imu () {
        // Retrieve the IMU from the hardware map
        imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);
    }
}
