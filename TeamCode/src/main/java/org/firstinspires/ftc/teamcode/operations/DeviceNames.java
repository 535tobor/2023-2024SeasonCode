package org.firstinspires.ftc.teamcode.operations;

public enum DeviceNames {
    DEFAULT_IMU("imu"),
    DEFAULT_CAMERA("Webcam 1");

    private final String name;

    DeviceNames(final String name) {this.name = name;}

    public String hardwareMapName() {
        return name;
    }
}
