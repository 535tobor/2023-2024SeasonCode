## HardwareMap
Any class that does not extend LinearOpMode or OpMode must include HardwareMap inside the method,
this way it can be set where the OpMode/LinearOpMode is extended.

### here is an example:
<pre>
public static void mapMotors (HardwareMap mapHardware, String frontLeftMotor, String frontRightMotor, String backLeftMotor, String backRightMotor){
        fl = mapHardware.dcMotor.get(frontLeftMotor);
        fr = mapHardware.dcMotor.get(frontRightMotor);
        bl = mapHardware.dcMotor.get(backLeftMotor);
        br = mapHardware.dcMotor.get(backRightMotor);
    }
</pre>
This is how hardwareMap can be used, by renaming in within the methods and calling it from the code that extends a class that allows the hardwareMap to be used.

### here is an example of the method being called:
<pre>
package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.operations.outputs.motors.drive.configureMotors.mapMotors;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class example extends OpMode {
    @Override
    public void init() {
        <b style="color: green">mapMotors(hardwareMap, "fr","fl","br","bl");</b>
    }

    @Override
    public void loop() {

    }
}
</pre>
In green is the mapMotors being called out off the example class and being ran inside the init method. This method will send hardwareMap to the mapMotors method and map the motors "fr", "fl", "br", and "bl".

## Telemetry
The same notation must be acquired for telemetry. If I wanted to call telemetry from a class that has no extensions that connect to the OpMode class than I need to call a value in my meathod that gets the telementry for me, using the Telementry module.

### here is an example of the method:
<pre>
public static void output(Telemetry display, Object output) {
        // display to telemetry without a caption
        display.addData("", output);
        display.update();
    }
</pre>
Again this works just like the HardwareMap module, but with telemetry. In order for telemetry to operate correctly outside of an extention of OpMode it must look for an input of telementry on the other side.
### here is an example of the output method being called:
<pre>
output(telemetry, "Robot Stopped.");
</pre>
once the output method is called it sends a message to the driver station that the robot has been stopped.
##### There are also other keywords that might need to be called such as Gamepad if your using gamepad1 or gamepad2 outside of an extension of OpMode.