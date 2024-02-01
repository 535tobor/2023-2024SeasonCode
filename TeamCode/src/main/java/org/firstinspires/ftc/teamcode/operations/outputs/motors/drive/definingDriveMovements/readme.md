# Defining Drive Movements

> This folder is where all the direct movements are put. If I want to tell the robot to move forward in autonomous then I would call a Java class from this folder.

Bellow is a code snip from EncoderTickDefinitions.java. The point of this code is to define what forward is to the robot so that it can be called in the code under the folder gameCode:
<pre>
public void forward(double inches, long seconds) {
        int finalTicks = (int) (inches * ticksPerInch);
        Encoders.clear();
        Encoders.target(-finalTicks,-finalTicks,-finalTicks,-finalTicks);
        Encoders.go();
        drive(-encoderSpeed,-encoderSpeed,-encoderSpeed,-encoderSpeed);
        sleep(seconds*1000);
    }
</pre>