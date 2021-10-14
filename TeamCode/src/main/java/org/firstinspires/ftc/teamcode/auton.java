package org.firstinspires.ftc.teamcode.Practice;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "auton")
public class auton extends LinearOpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;




    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        waitForStart();
        frontLeft.setPower(.3);
        frontRight.setPower(.3);
        backLeft.setPower(.3);
        backRight.setPower(.3);
        sleep(2000);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(2000);

        frontLeft.setPower(.3);
        frontRight.setPower(-.3);
        backLeft.setPower(.3);
        backRight.setPower(-.3);
        sleep(2000);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(2000);

        frontLeft.setPower(-.3);
        frontRight.setPower(-.3);
        backLeft.setPower(-.3);
        backRight.setPower(-.3);
        sleep(2000);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(2000);


    }
}
