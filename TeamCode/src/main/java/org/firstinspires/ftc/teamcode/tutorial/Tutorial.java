package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous(name="Tutorial")
public class Tutorial extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.speak("pigeon");
        sleep( 5000);
    }
}
