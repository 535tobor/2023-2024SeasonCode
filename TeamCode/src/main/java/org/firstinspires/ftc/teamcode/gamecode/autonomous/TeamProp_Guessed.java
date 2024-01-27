package org.firstinspires.ftc.teamcode.gamecode.autonomous;

import static org.firstinspires.ftc.teamcode.operations.inputs.AprilTag.initAprilTag;
import static org.firstinspires.ftc.teamcode.operations.inputs.Target_inputs.imu;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.bl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.br;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fl;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.forwardMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.fr;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.ConfigureMotors.mapMotors;
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.forwardAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.operations.Target_operations;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Wheels;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Encoders;

@Autonomous(name="Chance Program (Team Prop)", group="auto")
@Disabled
public class TeamProp_Guessed extends Target_operations {

    DcMotor arm;
    DistanceSensor sensorRange;
    double degrees;

    @Override
    public void runOpMode() throws InterruptedException {
        // LinearOpMode that calls a different form of OpMode:
        runInit();
        while(opModeInInit()){runInitLoop();}
        waitForStart();
        runStart();
        if (isStopRequested()){runStop();} // stop OpMode if the button is pressed
        while (opModeIsActive()) {runLoop();}
    }

    @Override
    public void runInit() {
        //arm = hardwareMap.dcMotor.get("arm");
        mapMotors(hardwareMap, Wheels.FRONT_LEFT.abbreviation(),Wheels.FRONT_RIGHT.abbreviation(),Wheels.BACK_LEFT.abbreviation(),Wheels.BACK_RIGHT.abbreviation());
        forwardMotors(true,false,true,false);
        sensorRange = hardwareMap.get(DistanceSensor.class, "eye");
        // ^ set motor directions
        initAprilTag(hardwareMap, "Webcam 1", telemetry);
        imu = hardwareMap.get(IMU.class, "imu");
        Encoders.use();
        Encoders.clear();



    }

    @Override
    public void runInitLoop(){}

    @Override
    public void runStart() {
        forwardAuto(30,5,100); // push pixel onto guessed tape

    }
    @Override
    public void runStop() {
        stopAll();
    }
    @Override
    public void runLoop() {
        telemetry.addData("distance ", sensorRange.getDistance(DistanceUnit.INCH));
        telemetry.addData("position FL ", fl.getCurrentPosition());
        telemetry.addData("position FR ", fr.getCurrentPosition());
        telemetry.addData("position BL ", bl.getCurrentPosition());
        telemetry.addData("position BR ", br.getCurrentPosition());
        telemetry.addData("degree ", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.update();
    }
}

