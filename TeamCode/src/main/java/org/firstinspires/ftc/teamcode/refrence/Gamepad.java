/* Copyright (c) 2015 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package org.firstinspires.ftc.teamcode.refrence;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;



@TeleOp(name = "Gamepad", group = "TEST")
// TeleOp is the set category for this program
@Disabled
public class Gamepad extends OpMode {

    DcMotor frontRightMotor;
    DcMotor frontLeftMotor;
    DcMotor backRightMotor;
    DcMotor backLeftMotor;
    double speed = 1; // sets the starting speed of all motors to 1


    @Override
    public void init() {
        frontRightMotor = hardwareMap.get(DcMotor.class,"frontRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class,"frontLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class,"backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class,"backLeftMotor");


        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status:", "Robot is Initialized");


    }

    @Override
    public void init_loop() { }

    @Override
    public void start() {
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {

        if (gamepad1.dpad_down) {
            // if hat up button is pressed then set speed to .5
            speed = .5;
        } else if (gamepad1.dpad_up) {
            // if hat up button is pressed then set speed to 1
            speed = 1;
        }

        if (gamepad1.right_trigger > 0.1) {
            // if the first game-pad's right trigger is pushed then the robot strives right
            frontRightMotor.setPower(.8 * speed);
            backRightMotor.setPower(-.8 * speed);
            frontLeftMotor.setPower(-.8 * speed);
            backLeftMotor.setPower(.8 * speed);
        } else if (gamepad1.left_trigger > 0.1) {
            // if the first game-pad's left trigger is pushed then the robot strives left
            frontRightMotor.setPower(-.8 * speed);
            backRightMotor.setPower(.8 * speed);
            frontLeftMotor.setPower(.8 * speed);
            backLeftMotor.setPower(-.8 * speed);
        } else {
            /* if nether trigger is pushed, however one or two joystick/s is/are then look for the following:
             if the right stick is pushed up then move the two right wheels forward, if the stick is moved backwards then do the opposite
             if the left stick is pushed up then move the two left wheels forward, if the stick is moved backwards then do the opposite */

            frontRightMotor.setPower(Range.clip(gamepad1.right_stick_y, -1, 1) * speed);
            backRightMotor.setPower(Range.clip(gamepad1.right_stick_y, -1, 1) * speed);
            frontLeftMotor.setPower(Range.clip(gamepad1.left_stick_y, -1, 1) * speed);
            backLeftMotor.setPower(Range.clip(gamepad1.left_stick_y, -1, 1) * speed);
        }


        telemetry.addData("Right Motor Power", frontRightMotor.getPower());
        telemetry.addData("Left Motor Power", frontLeftMotor.getPower());
        telemetry.addData("0", "frMotor: " + frontRightMotor.getCurrentPosition());
        telemetry.addData("1", "flMotor: " + frontLeftMotor.getCurrentPosition());
        telemetry.addData("2", "brMotor: " + backRightMotor.getCurrentPosition());
        telemetry.addData("3", "blMotor: " + backLeftMotor.getCurrentPosition());
    }
}