package org.firstinspires.ftc.teamcode.operations.inputs;

import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection.*;
import static org.firstinspires.ftc.teamcode.operations.inputs.TargetInputs.*;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;


public class Imu {

    public static void imuGet (HardwareMap mapHardware, String hardwareMapImuName, String logoFacing, String usbFacing) {
        // get imu from hardware map and set the directions to the directions of the robot's brain
        imu = mapHardware.get(IMU .class, hardwareMapImuName);
        // Retrieve the IMU from the hardware map
        IMU.Parameters parameters = null;
        // Adjust the orientation parameters to match your robot
        if (logoFacing == UP.name() && usbFacing == FORWARD.name()) {
            parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                    UP,
                    RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        }

        else if (logoFacing == LEFT.name() && usbFacing == UP.name()) {
            parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                    RevHubOrientationOnRobot.UsbFacingDirection.UP));
        }

        else if (logoFacing == RIGHT.name() && usbFacing == UP.name()) {
            parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                    RevHubOrientationOnRobot.UsbFacingDirection.UP));
        }

        else if (logoFacing == FORWARD.name() && usbFacing == UP.name()) {
            parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                    RevHubOrientationOnRobot.UsbFacingDirection.UP));
        }

        else if (logoFacing == BACKWARD.name() && usbFacing == UP.name()) {
            parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
                    RevHubOrientationOnRobot.UsbFacingDirection.UP));
        }

        else if (logoFacing == FORWARD.name() && usbFacing == RIGHT.name()) {
            parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                    RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));
        }

        else if (logoFacing == UP.name() && usbFacing == BACKWARD.name()) {
            parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                    UP,
                    RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        }

        else if (logoFacing == DOWN.name() && usbFacing == FORWARD.name()) {
            parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.DOWN,
                    RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        }

        else if (logoFacing == FORWARD.name() && usbFacing == LEFT.name()) {
            parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                    RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
        }
        imu.initialize(parameters);
    }

    public static void imuReset(boolean button) {
        if (button) {
            imu.resetYaw();
        }
    }
}
