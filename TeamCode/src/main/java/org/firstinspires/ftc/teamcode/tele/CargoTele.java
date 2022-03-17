package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "H.A.T.E.")
public class CargoTele extends LinearOpMode{

    DcMotor fl; //no comment//;
    DcMotor br;
    DcMotor fr;
    DcMotor bl;

    @Override
    public void runOpMode() throws InterruptedException {
        //bs
        fl = hardwareMap.dcMotor.get("frontLeft");
        fr = hardwareMap.dcMotor.get("Spite");
        br = hardwareMap.dcMotor.get("haTRED");
        bl = hardwareMap.dcMotor.get("slamshead");

        waitForStart();
        
        //ass day
    }
}
