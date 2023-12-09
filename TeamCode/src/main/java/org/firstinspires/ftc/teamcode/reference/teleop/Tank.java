package org.firstinspires.ftc.teamcode.reference.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp /* this program will fall under TeleOp, if it said
@Autonomous then it would fall under the autonomous
catigory on the drop down screen. */
@Disabled
public class Tank extends OpMode{

    /* Declare OpMode members. */
    public DcMotor  backRight;
    public DcMotor  backLeft;
    public DcMotor  frontRight;
    public DcMotor  frontLeft;
    @Override
    public void init() {
        // Define and Initialize Motors
        backLeft  = hardwareMap.get(DcMotor.class, "bl");
        backRight = hardwareMap.get(DcMotor.class, "br");
        frontLeft  = hardwareMap.get(DcMotor.class, "fl");
        frontRight = hardwareMap.get(DcMotor.class, "fr");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);

        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData(">", "Ready.");    // initialized and ready to go.
    }

    @Override
    public void init_loop() { // loop that runs after initialization has been carried.
    }

    @Override
    public void start() {
        telemetry.addData(">", "Go!");
    } /* the play button has been
    clicked and the robot is running! */

    @Override
    public void loop() { // loop that runs after the play button has been pushed.
        double left;
        double right;
        /* ^ defines what type of variable left and right are */

        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;
        /* ^ stores gamepad1's right stick and left stick up and down moves into variables
        left and right */

        frontLeft.setPower(left);
        backLeft.setPower(left);
        frontRight.setPower(right);
        backRight.setPower(right);
        /* ^ every left motors are set to the left joystick's up and down power, while
        the right motors are set to the right joystick's up and down power. */
    }

    @Override
    public void stop() {
        telemetry.addData(">", "Robot is Stopped."); /* Send the message to the driver's station
        that the robot is now stopped.*/
    }
}