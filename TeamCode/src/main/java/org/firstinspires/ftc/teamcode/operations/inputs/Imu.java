package org.firstinspires.ftc.teamcode.operations.inputs;

import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection.*;
import static com.qualcomm.robotcore.hardware.IMU.*;
import static org.firstinspires.ftc.teamcode.operations.inputs.TargetInputs.*;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import java.util.Objects;


public class Imu {

    public static void imuGet (HardwareMap mapHardware, String hardwareMapImuName, String logoFacing, String usbFacing) {
        // get imu from hardware map and set the directions to the directions of the robot's brain
        imu = mapHardware.get(IMU.class, hardwareMapImuName);
        // Retrieve the IMU from the hardware map
        Parameters parameters = null;
        // Adjust the orientation parameters to match your robot
        if (Objects.equals(logoFacing, UP.name()) && Objects.equals(usbFacing, FORWARD.name())) {
            parameters = new Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.UP,
                    RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        }

        else if (Objects.equals(logoFacing, LEFT.name()) && Objects.equals(usbFacing, UP.name())) {
            parameters = new Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                    RevHubOrientationOnRobot.UsbFacingDirection.UP));
        }

        else if (Objects.equals(logoFacing, RIGHT.name()) && Objects.equals(usbFacing, UP.name())) {
            parameters = new Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                    RevHubOrientationOnRobot.UsbFacingDirection.UP));
        }

        else if (Objects.equals(logoFacing, FORWARD.name()) && Objects.equals(usbFacing, UP.name())) {
            parameters = new Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                    RevHubOrientationOnRobot.UsbFacingDirection.UP));
        }

        else if (Objects.equals(logoFacing, BACKWARD.name()) && Objects.equals(usbFacing, UP.name())) {
            parameters = new Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
                    RevHubOrientationOnRobot.UsbFacingDirection.UP));
        }

        else if (Objects.equals(logoFacing, FORWARD.name()) && Objects.equals(usbFacing, RIGHT.name())) {
            parameters = new Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                    RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));
        }

        else if (Objects.equals(logoFacing, UP.name()) && Objects.equals(usbFacing, BACKWARD.name())) {
            parameters = new Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.UP,
                    RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        }

        else if (Objects.equals(logoFacing, DOWN.name()) && Objects.equals(usbFacing, FORWARD.name())) {
            parameters = new Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.DOWN,
                    RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        }

        else if (Objects.equals(logoFacing, FORWARD.name()) && Objects.equals(usbFacing, LEFT.name())) {
            parameters = new Parameters(new RevHubOrientationOnRobot(
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
