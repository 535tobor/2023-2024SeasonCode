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
import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.definingDriveMovements.EncoderTickDefinitions.turnLeftAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.operations.Target_operations;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Encoders;
import org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.Wheels;

@Autonomous(name="Blue, Board Side 2 -> Team Props", group="auto")
@Disabled // test
public class TeamProp_Find extends Target_operations {

    DcMotor arm;
    DistanceSensor sensorRange;

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
        mapMotors(hardwareMap, Wheels.FRONT_LEFT.abbreviation(), Wheels.FRONT_RIGHT.abbreviation(), Wheels.BACK_LEFT.abbreviation(), Wheels.BACK_RIGHT.abbreviation());
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
        //closeClawSet();
        forwardAuto(21,3,500);
        if (sensorRange.getDistance(DistanceUnit.INCH) < 7) { // zone 2
            //openClawSet();
        }
        else {
            turnLeftAuto(180*20, 3,500); // zone 1
            if (sensorRange.getDistance(DistanceUnit.INCH) < 7) {
                //openClawSet();
            }
            else {
                turnLeftAuto(180*20*2, 3,500);
                forwardAuto(3,3,500);
                //openClawSet();
            }
        }
        }


    @Override
    public void runStop() {
        stopAll();
    }
    @Override
    public void runLoop() {

        telemetry.addData("position FL ", fl.getCurrentPosition());
        telemetry.addData("position FR ", fr.getCurrentPosition());
        telemetry.addData("position BL ", bl.getCurrentPosition());
        telemetry.addData("position BR ", br.getCurrentPosition());
        telemetry.update();
    }
}

