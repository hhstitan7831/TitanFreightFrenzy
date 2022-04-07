package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "H.A.T.E.")
public class CargoTele extends LinearOpMode {

    DcMotor fl; //no comment//;
    DcMotor br;
    DcMotor fr;
    DcMotor bl;

    @Override
    public void runOpMode() throws InterruptedException {
        //bs
        fl = hardwareMap.dcMotor.get("frontLeft");
        br = hardwareMap.dcMotor.get("backRight");
        fr = hardwareMap.dcMotor.get("frontRight");
        bl = hardwareMap.dcMotor.get("backLeft");

        waitForStart();

        fl.setPower(-.9);
        fr.setPower(-.9);
        br.setPower(.9);
        bl.setPower(.9);
        sleep(10000);

        fl.setPower(.9);
        fr.setPower(-.9);
        br.setPower(-.9);
        bl.setPower(.9);
        sleep(5000);

    }
}
