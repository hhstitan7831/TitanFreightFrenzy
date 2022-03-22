package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "HATE ("blu")
public class CargoAuto extends LinearOpMode{

    DcMotor fl;
    DcMotor br;
    DcMotor fr;
    DcMotor bl;

        @Override
        public void runOpMode()throws InterruptedException{

}
        fl= hardwareMap.dcMotor.get("frontLeft");
        fr = hardwareMap.dcMotor.get("Spite");
        br = hardwareMap.dcMotor.get("haTRED");
        bl = hardwareMap.dcMotor.get("slamshead");

        waitForStart();
        }
